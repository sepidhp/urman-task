package com.utechia.presentation.exchanges

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.utechia.domain.model.Exchange
import com.utechia.domain.model.ExchangeName
import com.utechia.domain.util.HttpErrors
import com.utechia.domain.util.Result
import com.utechia.presentation.R
import com.utechia.presentation.base.BaseFragment
import com.utechia.presentation.databinding.FragmentExchangeBinding
import com.utechia.presentation.databinding.MenuLanguageOptionsBinding
import com.utechia.presentation.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ExchangeFragment :
    BaseFragment<FragmentExchangeBinding>(R.layout.fragment_exchange) {
    private val viewModel: ExchangeViewModel by viewModels()
    private val clickHandler = ClickHandler(this)
    private val exchangeAdapter by lazy { ExchangeAdapter(requireActivity()) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupObservers() {
        viewModel.run {
            observe(getToken(), ::initToken)
            observe(getSocketException(), ::initSocketException)
            observe(getExchangeNames(), ::initExchangeNames)
            observe(getExchanges(), ::initExchanges)
        }
    }

    private fun initToken(result: Result<String>) {
        if (result is Result.Error) {
            showRetry(true)
            showLoading(false)
            result.error.message?.let {
                Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
            }
        } else if (result is Result.Loading) {
            showLoading(result.loading)
        }
    }

    private fun initSocketException(exception: Exception) {
        showRetry(true)
        showLoading(false)
    }

    private fun initExchangeNames(result: Result<List<ExchangeName>>?) {
        if (result is Result.Success) {
            viewModel.listenPriceEvent(result.data)
            viewModel.connectSocket()
        } else if (result is Result.Error) {
            if (result.error.status == HttpErrors.Unauthorized) {
                //handle expired token
                viewModel.fetchGuestToken()
            } else {
                showRetry(true)
                showLoading(false)
                result.error.message?.let {
                    Toast.makeText(requireContext(), it, Toast.LENGTH_SHORT).show()
                }
            }
        } else if (result is Result.Loading) {
            showLoading(result.loading)
        }
    }

    private fun initExchanges(result: List<Exchange>) {
        updateList(result)
    }

    private fun showLoading(isLoading: Boolean) {
        binding.isLoading = isLoading
    }

    private fun updateList(data: List<Exchange>) {
        showLoading(false)
        showRetry(false)
        exchangeAdapter.updateDataSet(data)
    }

    private fun showRetry(showRetry: Boolean) {
        binding.loadingHasError = showRetry
    }

    private fun setupUI() {
        binding.rvExchanges.apply {
            adapter = exchangeAdapter
            LinearSnapHelper().attachToRecyclerView(this)
        }
        binding.clickHandler = clickHandler
    }

    inner class ClickHandler(fragment: ExchangeFragment) {
        fun onRetryButtonClicked(view: View) {
            showRetry(false)
            viewModel.fetchExchanges()
        }

        fun onLanguageButtonClicked(view: View) {
            val bindingMenu: MenuLanguageOptionsBinding =
                MenuLanguageOptionsBinding.inflate(
                    layoutInflater
                )
            val popupWindow = PopupWindow(
                bindingMenu.root,
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            popupWindow.apply {
                isOutsideTouchable = true
                isFocusable = true
                showAsDropDown(binding.cnsChangeLanguage, -30, 0, Gravity.TOP)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewModel.disconnectSocket()
    }
}
package com.utechia.presentation.exchanges

import android.app.Activity
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.PopupWindow
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearSnapHelper
import com.utechia.domain.model.Exchange
import com.utechia.domain.util.HttpErrors
import com.utechia.domain.util.Result
import com.utechia.presentation.R
import com.utechia.presentation.base.BaseFragment
import com.utechia.presentation.databinding.FragmentHomeBinding
import com.utechia.presentation.databinding.MenuLanguageOptionsBinding
import com.utechia.presentation.util.observe
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment :
    BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
    private val viewModel: ExchangeViewModel by viewModels()
    private val clickHandler = ClickHandler(this)
    private val exchangeAdapter = ExchangeAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupObservers()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setStatusBarGradiant(requireActivity())
        setupUI()
    }

    private fun setupObservers() {
        viewModel.run {
            observe(getExchanges(), ::initExchanges)
        }
    }

    private fun initExchanges(result: Result<List<Exchange>>?) {
        if (result is Result.Success) {
            updateList(result.data)
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
        val snapHelper = LinearSnapHelper()
        binding.rvExchanges.apply {
            adapter = exchangeAdapter
            snapHelper.attachToRecyclerView(this)
        }
        binding.clickHandler = clickHandler
    }

    inner class ClickHandler(fragment: HomeFragment) {
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

    private fun setStatusBarGradiant(activity: Activity) {
        val window: Window = activity.window
        val background =
            ContextCompat.getDrawable(activity, R.drawable.bg_rect_solid_gradient_flat)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        window.statusBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
        window.navigationBarColor = ContextCompat.getColor(activity, android.R.color.transparent)
        window.setBackgroundDrawable(background)
    }
}
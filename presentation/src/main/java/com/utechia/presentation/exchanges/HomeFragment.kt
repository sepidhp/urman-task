package com.utechia.presentation.exchanges

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.PopupWindow
import android.widget.Toast
import androidx.fragment.app.viewModels
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
class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {
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

    private fun setupObservers() {
        viewModel.run {
            observe(getExchanges(), ::initPosts)
        }
    }

    private fun initPosts(result: Result<List<Exchange>>?) {
        if (result is Result.Success) {
            updateList(result.data)
        } else if (result is Result.Error) {
            if (result.error.status == HttpErrors.Unauthorized) {
                //handle expired token
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
//        binding.isLoading = isLoading
    }

    private fun updateList(data: List<Exchange>) {
        showLoading(false)
        showRetry(false)
        exchangeAdapter.upDateDataSet(data)
    }

    private fun showRetry(showRetry: Boolean) {
//        binding.loadingHasError = showRetry
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.clickHandler = clickHandler
    }

    inner class ClickHandler(fragment: HomeFragment) {
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
}
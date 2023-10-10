package com.utechia.presentation.exchanges

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.utechia.domain.model.Exchange
import dagger.hilt.android.AndroidEntryPoint
import com.utechia.domain.util.HttpErrors
import com.utechia.presentation.R
import com.utechia.presentation.base.BaseFragment
import com.utechia.presentation.util.observe
import com.utechia.domain.util.Result
import com.utechia.presentation.databinding.FragmentExchangesBinding

@AndroidEntryPoint
class ExchangesFragment : BaseFragment<FragmentExchangesBinding>(R.layout.fragment_exchanges) {
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
        binding.isLoading = isLoading
    }

    private fun updateList(data: List<Exchange>) {
        showLoading(false)
        showRetry(false)
        exchangeAdapter.upDateDataSet(data)
    }

    private fun showRetry(showRetry: Boolean) {
        binding.loadingHasError = showRetry
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI() {
        binding.rvExchanges.adapter = exchangeAdapter
        binding.clickHandler = clickHandler
    }

    inner class ClickHandler(fragment: ExchangesFragment) {

        fun onRetryButtonClicked(view: View) {
            showRetry(false)
            viewModel.fetchExchanges()
        }
    }
}
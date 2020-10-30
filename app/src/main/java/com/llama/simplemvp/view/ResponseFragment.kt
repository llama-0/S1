package com.llama.simplemvp.view

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.llama.simplemvp.App
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.ResponseContract
import com.llama.simplemvp.presenter.ResponsePresenter
import kotlinx.android.synthetic.main.fragment_response.*

class ResponseFragment : Fragment(R.layout.fragment_response), ResponseContract.View {

    private var presenter: ResponseContract.Presenter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as AppCompatActivity).supportActionBar?.setDisplayHomeAsUpEnabled(true)

        initPresenter()
    }

    private fun initPresenter() {
        activity?.application.let { app ->
            if (app is App) {
                app.model?.let {
                    presenter = ResponsePresenter(this, it, resources)
                }
            }
        }
        presenter?.initView()
    }

    override fun showMessage(message: String) {
        tvHello.text = message
    }

    override fun showTextViewBackgroundColor() {
        val color: Int = requireArguments().getInt(STR_COLOR, R.color.colorTextViewBackgroundDefault)
        tvHello.setBackgroundColor(color)
    }

    companion object {
        private const val STR_COLOR = "STR_COLOR"

        fun newInstance(color: Int): ResponseFragment {
            val args = Bundle()
            args.putInt(STR_COLOR, color)

            val fragment = ResponseFragment()
            fragment.arguments = args
            return fragment
        }
    }
}
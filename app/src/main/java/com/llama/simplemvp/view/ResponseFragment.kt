package com.llama.simplemvp.view

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.llama.simplemvp.App
import com.llama.simplemvp.R
import com.llama.simplemvp.contract.ResponseContract
import com.llama.simplemvp.presenter.ResponsePresenter
import kotlinx.android.synthetic.main.fragment_response.*

class ResponseFragment : Fragment(), ResponseContract.View {

    private var presenter: ResponseContract.Presenter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_response, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initPresenter()
        val color: Int = arguments!!.getInt(STR_COLOR, Color.GRAY)
        tvHello.setBackgroundColor(color)
    }

    private fun initPresenter() {
        (activity?.application as App).model?.let {
            presenter = ResponsePresenter(this, it, resources)
        }
        presenter?.initView()
    }

    override fun showMessage(message: String) {
        tvHello.text = message
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
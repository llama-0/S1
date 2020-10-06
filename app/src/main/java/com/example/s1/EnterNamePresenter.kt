import com.example.s1.EnterNameContract
import com.example.s1.Model

class EnterNamePresenter(
    private val view: EnterNameContract.View,
    private val model: Model?
) : EnterNameContract.Presenter {

    override fun onShowNameButtonClicked(text: String) {
        saveText(text)
        view.showMessage()
    }

    override fun retrieveText() {
        val text = model?.prefs?.getString(INPUT_KEY, null)
        text?.let { view.showText(it) }
    }

    override fun saveText(text: String) {
        model?.prefs?.edit()?.putString(INPUT_KEY,  text)?.apply()
    }

    companion object {
        private const val INPUT_KEY = "INPUT_KEY"
    }
}
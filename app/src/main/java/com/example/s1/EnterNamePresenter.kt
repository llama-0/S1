import com.example.s1.EnterNameContract
import com.example.s1.Model

class EnterNamePresenter : EnterNameContract.Presenter {

    override fun saveText(input: Model?, text: String) {
        input?.prefs?.edit()?.putString(INPUT_KEY,  text)?.apply()
    }

    override fun retrieveText(input: Model?): String? =
        input?.prefs?.getString(INPUT_KEY, null)

    companion object {
        private const val INPUT_KEY = "INPUT_KEY"
    }
}
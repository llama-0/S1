import android.content.SharedPreferences
import com.example.s1.ContractMain

class MainPresenter : ContractMain.Presenter {

    companion object {
        private const val INPUT_KEY = "INPUT_KEY"
    }

    override fun saveText(prefs: SharedPreferences, text: String) {
        prefs.edit().putString(INPUT_KEY,  text).apply()
    }

    override fun retrieveText(prefs: SharedPreferences): String? =
        prefs.getString(INPUT_KEY, null)
}
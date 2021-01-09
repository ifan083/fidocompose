package com.example.fidoreregistration

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.fidoreregistration.activate3ds.Activate3DSCredentialsFragment
import com.example.fidoreregistration.activate3ds.Activate3DSFragment
import com.example.fidoreregistration.complete.ReRegistrationCompleteFragment
import com.example.fidoreregistration.fido.FidoRegistrationFragment
import com.example.fidoreregistration.methodchooser.RegistrationMethodChooser
import com.example.fidoreregistration.nativelogin.NativeLoginFragment
import com.example.fidoreregistration.questionnaire.QuestionnaireFragment
import com.example.fidoreregistration.questionnaire.data.ReRegistrationActivity
import com.example.fidoreregistration.questionnaire.data.ReRegistrationFragment
import com.example.fidoreregistration.wizard.Wizard

class MainActivity : AppCompatActivity(), FragmentNavigation, ActivityNavigation {

    companion object {
        private const val ENTRY = "entry"

        fun start(context: Context, entry: Wizard.Entry) {
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra(ENTRY, "entry")
            }
            context.startActivity(intent)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            pushFragment(QuestionnaireFragment.from())
        }
    }

    override fun toFragment(destination: Wizard.FragmentDestination) {
        val fragment = when (destination) {
            is ReRegistrationFragment.FidoRegistration -> FidoRegistrationFragment.create(
                destination.eUser
            )
            ReRegistrationFragment.NativeLogin -> NativeLoginFragment.create()
            ReRegistrationFragment.RegistrationMethodChooser -> RegistrationMethodChooser.create()
            ReRegistrationFragment.Activate3DSCardSelection -> Activate3DSFragment.create()
            ReRegistrationFragment.Activate3DSCredentials -> Activate3DSCredentialsFragment.create()
            is ReRegistrationFragment.Complete -> ReRegistrationCompleteFragment.create(
                destination.title,
                destination.subtitle
            )
            else -> QuestionnaireFragment.from()
        }
        pushFragment(fragment)
    }

    override fun goBack() = popFragment()

    override fun toActivity(destination: Wizard.ActivityDestination) {
        val message = when (destination) {
            ReRegistrationActivity.AssetOverview -> "asset overview"
            ReRegistrationActivity.OnBoarding -> "onboarding"
            else -> "welcome screen"
        }

        Toast.makeText(this, "goto: $message", Toast.LENGTH_SHORT).show()
    }

    private fun pushFragment(fragment: BaseFragment) {
        supportFragmentManager.commit {
            add(R.id.fragment_container_view, fragment)
        }
    }

    private fun popFragment() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
            return
        }
        finish()
    }
}

interface FragmentNavigation {
    fun toFragment(destination: Wizard.FragmentDestination)
    fun goBack()
}

interface ActivityNavigation {
    fun toActivity(destination: Wizard.ActivityDestination)
}
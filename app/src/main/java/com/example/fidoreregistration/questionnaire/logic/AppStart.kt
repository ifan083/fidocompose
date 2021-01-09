package com.example.fidoreregistration.questionnaire.logic

import com.example.fidoreregistration.questionnaire.data.ReRegistration
import com.example.fidoreregistration.questionnaire.data.WizardStep
import com.example.fidoreregistration.questionnaire.ui.QuestionScreenDescription
import com.example.fidoreregistration.wizard.Action

class AppStart : WizardStep {

    override fun destination() = ReRegistration.AppStart

    override fun screenDescription() = QuestionScreenDescription(
        title = "Thank you for downloading the PostFinance app",
        subtitle = "Do you already have an e-finance login?",
        posBtnText = "Yes, ready to go",
        negBtnText = "Not yet",
        imgUrl = QuestionScreenDescription.Img.BALLOONS.url,
        canGoBack = false,
        canExit = false
    )

    override fun positiveAction() = Action.Continue(ReRegistration.SetupTouchIdLogin)

    override fun negativeAction() = Action.Continue(ReRegistration.ExistingCustomerCheck)


}
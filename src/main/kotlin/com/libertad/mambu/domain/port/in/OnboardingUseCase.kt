package com.libertad.mambu.domain.port.`in`

interface OnboardingUseCase {

    fun initProcess(data:  HashMap<String, Any>): HashMap<String, Any>
}
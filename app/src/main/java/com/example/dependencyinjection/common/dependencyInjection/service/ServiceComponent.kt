package com.example.dependencyinjection.common.dependencyInjection.service

import com.example.dependencyinjection.common.dependencyInjection.presentation.PresentationComponent
import dagger.Subcomponent

@Subcomponent(modules = [ServiceModule::class])
interface ServiceComponent {

}
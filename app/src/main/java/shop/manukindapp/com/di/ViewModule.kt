package shop.manukindapp.com.di

import shop.manukindapp.com.ui.viewmodel.AppViewModel
import shop.manukindapp.com.ui.viewmodel.CartViewModel
import shop.manukindapp.com.ui.viewmodel.CheckoutViewModel
import shop.manukindapp.com.ui.viewmodel.OnboardingViewModel
import shop.manukindapp.com.ui.viewmodel.OrderViewModel
import shop.manukindapp.com.ui.viewmodel.ProductDetailsViewModel
import shop.manukindapp.com.ui.viewmodel.ProductViewModel
import shop.manukindapp.com.ui.viewmodel.SplashViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModule = module {
    viewModel {
        AppViewModel(
            cartRepository = get()
        )
    }

    viewModel {
        SplashViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        OnboardingViewModel(
            onboardingRepository = get()
        )
    }

    viewModel {
        ProductViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        ProductDetailsViewModel(
            productRepository = get(),
            cartRepository = get(),
        )
    }

    viewModel {
        CheckoutViewModel(
            cartRepository = get(),
            productRepository = get(),
            orderRepository = get(),
        )
    }

    viewModel {
        CartViewModel(
            cartRepository = get(),
            productRepository = get(),
        )
    }

    viewModel {
        OrderViewModel(
            orderRepository = get(),
        )
    }
}
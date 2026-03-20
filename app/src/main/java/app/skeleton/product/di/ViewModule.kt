package app.skeleton.product.di

import app.skeleton.product.ui.viewmodel.AppViewModel
import app.skeleton.product.ui.viewmodel.CartViewModel
import app.skeleton.product.ui.viewmodel.CheckoutViewModel
import app.skeleton.product.ui.viewmodel.OnboardingViewModel
import app.skeleton.product.ui.viewmodel.OrderViewModel
import app.skeleton.product.ui.viewmodel.ProductDetailsViewModel
import app.skeleton.product.ui.viewmodel.ProductViewModel
import app.skeleton.product.ui.viewmodel.SplashViewModel
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
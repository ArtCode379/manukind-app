# Manukind App — Android Shop Application

## Project Type
Shop (product catalog with cart and checkout)

## Company
- **Name:** MANUKIND UK LTD
- **Business:** Board games, souvenirs, and collectibles retail
- **Website:** http://manukindukplus.uk

## App Identity
- **App Name:** Manukind App
- **Package:** shop.manukindapp.com
- **Prefix:** MNKPA (replaces "Skeleton" in all class names, database names, etc.)
- **Source package:** Rename `app.skeleton.product` → `shop.manukindapp.com` (all files, imports, build.gradle, manifest)
- **Version:** 1.0.0

## Design / Theme
Luxury minimalist style inspired by the website:
- **Primary (dark):** #1A1A1A
- **Accent / Gold:** #C9A96E
- **Text primary:** #333333
- **Text secondary:** #666666
- **Surface light:** #FAFAFA
- **Background:** #F5F5F5
- **Light theme** as default; provide a dark variant too
- Rename the theme to `ManukindAppTheme`
- Clean, sophisticated look with generous whitespace

## Content Requirements

### Products
Create 5 categories relevant to a board games & souvenirs store:
1. Board Games
2. Card Games
3. Puzzles & Brain Teasers
4. Collectible Figurines
5. Gift Sets & Bundles

Populate 12+ products across categories with realistic names, descriptions, and prices in GBP (£9.99–£79.99).

### Screens (all required)
1. **Splash** — Gold gradient background, app logo fade-in animation, 2.5s delay
2. **Onboarding** — HorizontalPager with 3 themed slides (Discover Games, Curated Collections, Easy Ordering), dot indicators, Skip/Next/Start buttons
3. **Home** — Featured carousel at top, category filter chips, 2-column product grid with "Add to Cart" buttons
4. **Product Details** — Full product image, category badge, price, description, Add to Cart button
5. **Cart** — Item cards with images, quantity +/- controls, total price, "Proceed to Checkout" button
6. **Checkout** — Shipping form fields (name, email, phone, address), styled "Confirm Order" button
7. **Checkout Dialog** — Order confirmation with order number and pickup-in-store message (expected within 24 hours)
8. **Orders** — Order history cards with date and items summary
9. **Settings** — Company name (MANUKIND UK LTD), Version 1.0.0, Customer Support link → http://manukindukplus.uk

### Images
Download real product-related stock photos from Unsplash/Pexels. Save to `app/src/main/res/drawable/`. Use for:
- 12+ product images
- 3 onboarding slide backgrounds
- Use `curl -L -o <path> "<url>"` to download images

## Technical Notes
- Language: Kotlin only, ALL UI text in English
- Architecture: MVVM + Koin DI (already in skeleton)
- Database: Room (already configured)
- Jetpack Compose for all UI
- After ALL changes, commit and push to `main`

## CRITICAL
- ALL user-facing text MUST be in English
- Rename ALL skeleton/Skeleton references to MNKPA/Manukind
- The app MUST compile without errors

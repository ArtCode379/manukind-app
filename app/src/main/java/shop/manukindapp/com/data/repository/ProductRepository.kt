package shop.manukindapp.com.data.repository

import shop.manukindapp.com.R
import shop.manukindapp.com.data.model.Product
import shop.manukindapp.com.data.model.ProductCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
    private val products: List<Product> = listOf(
        // Board Games
        Product(
            id = 1,
            title = "Catan: The Board Game",
            description = "Trade, build, and settle the island of Catan in this award-winning strategy game. Collect and trade resources to build roads, settlements, and cities. Compete with other players to become the dominant force on the island. Perfect for 3-4 players.",
            category = ProductCategory.BoardGames,
            price = 44.99,
            imageRes = R.drawable.product_catan,
        ),
        Product(
            id = 2,
            title = "Ticket to Ride: European Edition",
            description = "Embark on a train adventure across Europe! Collect cards to claim railway routes connecting iconic cities. Build the longest continuous route and complete destination tickets for bonus points. A beloved family game for 2-5 players.",
            category = ProductCategory.BoardGames,
            price = 39.99,
            imageRes = R.drawable.product_ticket_to_ride,
        ),
        Product(
            id = 3,
            title = "Carcassonne Classic",
            description = "Build a medieval landscape tile by tile. Place followers as knights, monks, farmers, or thieves to score points. Simple to learn yet deeply strategic, this classic tile-laying game is perfect for casual and experienced gamers alike.",
            category = ProductCategory.BoardGames,
            price = 29.99,
            imageRes = R.drawable.product_carcassonne,
        ),

        // Card Games
        Product(
            id = 4,
            title = "Exploding Kittens",
            description = "A highly strategic, kitty-powered version of Russian Roulette. Draw cards until someone draws an Exploding Kitten and is out of the game — unless they have a Defuse card. Fast-paced fun for 2-5 players.",
            category = ProductCategory.CardGames,
            price = 19.99,
            imageRes = R.drawable.product_exploding_kittens,
        ),
        Product(
            id = 5,
            title = "Uno Deluxe Card Game",
            description = "The classic card game everyone loves, now in a premium deluxe edition with a sturdy card tray. Match colours and numbers, use action cards to shake things up, and be the first to empty your hand. For 2-10 players.",
            category = ProductCategory.CardGames,
            price = 12.99,
            imageRes = R.drawable.product_uno_deluxe,
        ),
        Product(
            id = 6,
            title = "Sushi Go Party!",
            description = "Pass sushi dishes around the table and pick your favourites. Collect the best combination of sushi dishes to score the most points. This expanded party edition includes a menu board and over 20 types of sushi to choose from.",
            category = ProductCategory.CardGames,
            price = 21.99,
            imageRes = R.drawable.product_sushi_go,
        ),

        // Puzzles & Brain Teasers
        Product(
            id = 7,
            title = "World Map 1000pc Puzzle",
            description = "Piece together a stunning illustrated world map with this premium 1000-piece jigsaw puzzle by Ravensburger. Features vibrant artwork with landmarks and wildlife from every continent. Perfect for puzzle enthusiasts.",
            category = ProductCategory.Puzzles,
            price = 14.99,
            imageRes = R.drawable.product_world_map_puzzle,
        ),
        Product(
            id = 8,
            title = "Rubik's Cube Classic 3x3",
            description = "The world's best-selling brain teaser. Over 43 quintillion possible combinations but only one solution. Challenge yourself to solve all six sides of this iconic 3x3 puzzle cube. A timeless classic for all ages.",
            category = ProductCategory.Puzzles,
            price = 9.99,
            imageRes = R.drawable.product_rubiks_cube,
        ),

        // Collectible Figurines
        Product(
            id = 9,
            title = "Dragon Knight Figurine",
            description = "Hand-painted fantasy dragon knight figurine crafted with exquisite detail. Standing 15cm tall, this collectible piece features a warrior mounted on a fierce dragon. Made from high-quality resin with a premium finish.",
            category = ProductCategory.Figurines,
            price = 34.99,
            imageRes = R.drawable.product_dragon_knight,
        ),
        Product(
            id = 10,
            title = "Medieval Castle Diorama Set",
            description = "An intricately detailed medieval castle diorama set with towers, walls, and miniature figures. Perfect as a display piece or for tabletop gaming. Includes drawbridge, keep, and courtyard with hand-painted details.",
            category = ProductCategory.Figurines,
            price = 59.99,
            imageRes = R.drawable.product_medieval_castle,
        ),
        Product(
            id = 11,
            title = "Fantasy Wizard Collection",
            description = "A set of three beautifully sculpted wizard figurines, each representing a different school of magic. Highly detailed robes, staffs, and spell effects make these pieces a must-have for any fantasy collector.",
            category = ProductCategory.Figurines,
            price = 24.99,
            imageRes = R.drawable.product_fantasy_wizard,
        ),

        // Gift Sets & Bundles
        Product(
            id = 12,
            title = "Family Game Night Bundle",
            description = "Everything you need for the perfect family game night! This premium bundle includes a selection of classic board games, card games, dice, and a score pad — all in a beautiful gift box. Ideal for 2-6 players.",
            category = ProductCategory.GiftSets,
            price = 79.99,
            imageRes = R.drawable.product_family_bundle,
        ),
        Product(
            id = 13,
            title = "Card Game Starter Pack",
            description = "The ultimate starter pack for card game enthusiasts. Includes three popular card games, premium card sleeves, and a handy carrying case. A perfect gift for anyone who loves quick, social games.",
            category = ProductCategory.GiftSets,
            price = 49.99,
            imageRes = R.drawable.product_card_starter,
        ),
    )

    fun observeById(id: Int): Flow<Product?> {
        val item = products.find { it.id == id }
        return flowOf(item)
    }

    fun getById(id: Int): Product? {
        return products.find { it.id == id }
    }

    fun observeAll(): Flow<List<Product>> {
        return flowOf(products)
    }
}

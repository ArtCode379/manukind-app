package shop.manukindapp.com.data.model

import androidx.annotation.StringRes
import shop.manukindapp.com.R

enum class ProductCategory(@field:StringRes val titleRes: Int) {
    BoardGames(R.string.category_board_games),
    CardGames(R.string.category_card_games),
    Puzzles(R.string.category_puzzles),
    Figurines(R.string.category_figurines),
    GiftSets(R.string.category_gift_sets),
}

package com.cookandroid.block7

import android.view.View
import android.widget.ImageView

class Item(GameActivity: GameActivity, nameItem: String, nameKorItem: String, itemImage: ImageView) {
    // 게임 액티비티
    var GameActivity: GameActivity = GameActivity

    // 아이템 이름
    var nameItem: String = nameItem

    var nameKorItem: String = nameKorItem

    // 아이템의 이미지뷰
    var itemImage : ImageView = itemImage

    // 아이템 획득 메소드
    fun getItme() {
        GameActivity.itemListOwned.add(this)
        GameActivity.itemListNotOwned.remove(this)
        GameActivity.itemListBroken.remove(this)
        updateItemVisibility()
    }

    // 아이템 잃는 메소드
    fun loseItem() {
        GameActivity.itemListOwned.remove(this)
        GameActivity.itemListNotOwned.add(this)
        GameActivity.itemListBroken.remove(this)
        updateItemVisibility()
    }

    // 아이템 고장 메소드
    fun breakItem() {
        GameActivity.itemListOwned.add(this)
        GameActivity.itemListBroken.add(this)
        GameActivity.itemListNotOwned.remove(this)
        updateItemVisibility()
    }

    // itemImage의 visivility를 업데이트 하는 함수
    fun updateItemVisibility() {
        for(item in GameActivity.itemListOwned) item.itemImage.visibility = View.VISIBLE
        for(item in GameActivity.itemListBroken) item.itemImage.visibility = View.VISIBLE
        for(item in GameActivity.itemListNotOwned) item.itemImage.visibility = View.GONE
    }
}
package com.miguelrr.capsshop.domain.usecases

import com.miguelrr.capsshop.CapsShopApp.Companion.idUserSP
import com.miguelrr.capsshop.data.ShoppingCarRepository
import javax.inject.Inject

class RemoveItemSCUseCase @Inject constructor(
    private val shoppingCarRepository: ShoppingCarRepository
) {
    suspend operator fun invoke(id : Int){
        val idUser = idUserSP.getId()
        shoppingCarRepository.deleteItem(id, idUser)
    }
}
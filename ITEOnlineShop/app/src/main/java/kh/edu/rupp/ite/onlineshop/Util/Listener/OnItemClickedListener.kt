package kh.edu.rupp.ite.onlineshop.Util.Listener

import kh.edu.rupp.ite.onlineshop.Model.PojoData.ProductModel

interface OnItemClickedListener {
    fun onProductClick(pos: Int, data: ProductModel)
}
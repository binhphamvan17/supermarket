package Controller;

import Func.ProductFunc;
import View.StoreView;
public class StoreController {
    private ProductFunc productDao;
    private StoreView view;

    public StoreController() {
        this.view = new StoreView();
        this.productDao = new ProductFunc();
    }

    public void showView() {
        view.showView();
        view.showListStore(productDao.readListProducts());
    }
}

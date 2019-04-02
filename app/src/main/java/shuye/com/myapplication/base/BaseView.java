package shuye.com.myapplication.base;

/**
 * MVP view 基类
 *
 * @author pengboboer
 * @date 2018/7/2
 */

public interface BaseView<T> {

    /**
     * 设置presenter
     * @param presenter 该view的presenter
     */
    void setPresenter(T presenter);
}

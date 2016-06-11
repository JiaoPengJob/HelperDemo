package com.jiaop.sugar;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jiaop.sugar.bean.Goods;
import com.jiaop.sugar.bean.Operator;

import java.math.BigDecimal;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 增加一条数据
     */
    private void insert() {
        Goods good = new Goods();
        good.setName("Coffee");
        good.setCost(new BigDecimal(30));
        good.setBn("123456");
        good.save();
    }

    /**
     * 查询一条数据
     */
    private void select() {
        Goods loadGood = Goods.findById(Goods.class, 1);
    }

    /**
     * 查询所有
     */
    private void selectAll() {
        List<Goods> goods = Goods.listAll(Goods.class);
    }

    /**
     * 更新一条数据
     */
    private void update() {
        Goods good2 = Goods.findById(Goods.class, 1);
        good2.setName("Rice");
        good2.save();
    }

    /**
     * 删除一条数据
     */
    private void delete() {
        Goods good2 = Goods.findById(Goods.class, 1);
        good2.delete();
    }

    /**
     * 删除表中所有内容
     */
    private void deleteAll() {
        Goods.deleteAll(Goods.class);
    }

    /**
     * 根据条件查询
     */
    private void findByQuery() {
        Goods.find(Goods.class, "name = ? and skuId = ?", "Coffee", "123");
    }

    /**
     * 查询一对多关系类
     */
    private void findClass() {
        // List<Goods> goods = Goods.find(Goods.class, "operator = ?", new String{operator.getName()});
        //或者
        Goods good = Goods.findById(Goods.class, 1);
        Operator o = good.getOperator();
    }

}

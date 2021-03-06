package com.zhenquan.doubanread.ui.activity

import android.content.Context
import android.support.annotation.DrawableRes
import android.support.v4.app.Fragment
import android.support.v7.app.ActionBarDrawerToggle
import android.view.View
import com.flyco.tablayout.listener.CustomTabEntity
import com.zhenquan.doubanread.R
import com.zhenquan.doubanread.base.BaseActivity
import com.zhenquan.doubanread.base.BaseFragment
import com.zhenquan.doubanread.ui.classfiy.ClassfiyFragment
import com.zhenquan.doubanread.ui.local.CircleFragment
import com.zhenquan.doubanread.ui.mine.MineFragment
import com.zhenquan.doubanread.ui.recommendation.RecommendationFragment
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast


class MainActivity : BaseActivity() {


    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initView(rootView: View?) {
//        EventBus.getDefault().register(this)
    }

    override fun initData() {
        super.initData()
        //初始化所有的fragment界面
        initAllPage()
        //初始化 toolbar
        initToolBar()
        //初始化 drawer
//        initDrawer()
    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    fun handleEventBus(userInfo: UserInfo) {
//        tv_user_entrance.text = userInfo.username
//        tv_user_entrance.setOnClickListener {
//            startActivity<UserInfoSettingActivity>()
//        }
//    }
//    /**
//     * 初始化 并设置 drawerLayout
//     */
//    private fun initDrawer() {
//        //设置drawer 滑动同步
//        dl_drawerlayout.addDrawerListener(
//                ActionBarDrawerToggle(this, dl_drawerlayout, t_toolbar, R.string.open, R.string.close).apply { syncState() }
//        )
//        //登陆注册
//
//        if (UserInfo.getUserIsLogin(this) ) {
//            val userLoginInfo = UserInfo.getUserLoginInfo(this)
//            tv_user_entrance.text = userLoginInfo.username
//            tv_user_entrance.setOnClickListener{
//                startActivity<UserInfoSettingActivity>()
//            }
//        }else{
//            tv_user_entrance.setOnClickListener {
//                startActivity<UserEntranceActivity>()
//            }
//        }
//
//
//        (0 until drawer_item_group.childCount).map { drawer_item_group.getChildAt(it) }.filter { it is SuperTextView }.forEach {
//            it.setOnClickListener { v ->
//                when (v) {
//                //购物车
//                    stv_shopping_cart -> {
//                        imgToast(R.mipmap.ic_action_category, "购物车")
//                    }
//                //余额
//                    stv_balance -> {
//                        imgToast(R.mipmap.ic_action_category, "余额")
//                    }
//                //礼券
//                    stv_coupon -> {
//                        imgToast(R.mipmap.ic_action_category, "礼券")
//                    }
//                //礼物
//                    stv_gift -> {
//                        imgToast(R.mipmap.ic_action_category, "礼物")
//                    }
//                //搜索
//                    stv_search -> {
//                        imgToast(R.mipmap.ic_action_category, "搜索")
//                    }
//                //设置
//                    stv_setting -> {
//                        imgToast(R.mipmap.ic_action_category, "设置")
//                    }
//                //反馈
//                    stv_feedback -> {
//                        imgToast(R.mipmap.ic_action_category, "反馈")
//                    }
//                }
//                dl_drawerlayout.closeDrawers()
//            }
//
//        }
//
//    }

    /**
     * 初始化 并配置 toolbar
     */
    private fun initToolBar() {
        setSupportActionBar(t_toolbar)
//        supportActionBar?.apply {
//            setHomeButtonEnabled(true)
//            setDisplayHomeAsUpEnabled(true)
//        }

        //MenuAction 点击事件
        action_search.setOnClickListener {
           startActivity<SearchBookActivity>()
        }
//        action_category.setOnClickListener {
//           startActivity<Json2SqlActivity>()
//        }
//        action_notification.setOnClickListener {
//            toast("action_notification")
//        }
    }

    /**
     * 主界面对用功能的fragment
     */
    private val mainFragments: ArrayList<Fragment>
            = arrayListOf(RecommendationFragment()
            , ClassfiyFragment()
            , MineFragment()
            , CircleFragment())

    /**
     * 初始化 程序页面
     */
    private fun initAllPage() {
        //推荐
        val recommendationTab = MyTabEntity(getString(R.string.page_recommendation), R.mipmap.ic_tab_recommendation_active, R.mipmap.ic_tab_recommendation_normal)
        //分类
        val originalWorkTab = MyTabEntity(getString(R.string.page_classfiy_work), R.mipmap.ic_tab_original_works_active, R.mipmap.ic_tab_original_works_normal)
        //我的
        val mineTab = MyTabEntity(getString(R.string.page_mine), R.mipmap.ic_tab_mine_active, R.mipmap.ic_tab_mine_normal)
        //
//        val localTab = MyTabEntity(getString(R.string.page_circle), R.mipmap.ic_tab_local_active, R.mipmap.ic_tab_local_normal)

        ctl_bottom_nvg.setTabData(arrayListOf(recommendationTab, originalWorkTab, mineTab)
                , this@MainActivity
                , R.id.fl_main_content
                , mainFragments)
    }


    /**
     * 获取主界面所有的fragment
     */
    fun getTabFragments(): ArrayList<Fragment> {
        return mainFragments
    }

    /**
     * 获取主界面 具体对应的fragment
     */
    inline fun <reified T : BaseFragment> getTabFragment(clazz: Class<T>): T? {
        return getTabFragments().find { it.javaClass == clazz }?.let { it as T }
    }


    override fun initListener() {
    }

    private class MyTabEntity(val customTabTitle: String, @DrawableRes val customTabSelectedIcon: Int, @DrawableRes val customTabUnSelectedIcon: Int) : CustomTabEntity {
        override fun getTabTitle(): String {
            return customTabTitle
        }

        override fun getTabSelectedIcon(): Int {
            return customTabSelectedIcon
        }

        override fun getTabUnselectedIcon(): Int {
            return customTabUnSelectedIcon
        }
    }

//    override fun onDestroy() {
//        super.onDestroy()
//        EventBus.getDefault().unregister(this)
//    }
}

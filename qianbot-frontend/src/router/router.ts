import { RouteRecordRaw } from "vue-router";
import accessEnum from "@/access/accessEnum";
import BasicLayout from "@/layouts/BasicLayout.vue";
import AppPage from "@/views/app/AppPage.vue";
import UserLayout from "@/layouts/UserLayout.vue";
import UserLoginPage from "@/views/user/UserLoginPage.vue";
import UserRegisterPage from "@/views/user/UserRegisterPage.vue";
import AppDetailPage from "@/views/app/AppDetailPage.vue";
import AppAnswerPage from "@/views/app/AppAnswerPage.vue";
import AppResultPage from "@/views/app/AppResultPage.vue";
import MyAppPage from "@/views/app/MyAppPage.vue";
import AppHistoryPage from "@/views/app/AppHistoryPage.vue";
import AdminLayout from "@/layouts/AdminLayout.vue";
import AdminPage from "@/views/admin/AdminPage.vue";
import ChartPage from "@/views/chart/ChartPage.vue";
import ChartHistoryPage from "@/views/chart/ChartHistoryPage.vue";
import UserCenter from "@/views/user/UserCenter.vue";
import OjPage from "@/views/oj/OjPage.vue";
import OjQuestionPage from "@/views/oj/OjQuestionPage.vue";
import OjQuestionAddPage from "@/views/oj/OjQuestionAddPage.vue";
import CodeSandboxPage from "@/views/codesandbox/CodeSandboxPage.vue";

const routes: Array<RouteRecordRaw> = [
  {
    path: "/",
    name: "首页",
    component: BasicLayout,
    meta: {
      index: 1,
      showInMenu: true,
      access: accessEnum.NOT_LOGIN,
    },
  },
  {
    path: "/",
    name: "首页",
    component: () => import("@/views/home/HomePage.vue"),
    meta: {
      showInMenu: false,
    },
  },
  {
    path: "/app",
    name: "答题应用",
    component: AppPage,
    meta: {
      index: 2,
      showInMenu: true,
      access: accessEnum.NOT_LOGIN,
    },
  },
  {
    path: "/app/detail/:id",
    name: "应用详情",
    component: AppDetailPage,
    props: true,
    meta: {
      showInMenu: false,
      access: accessEnum.NOT_LOGIN,
    },
  },
  {
    path: "/app/answer/:appId",
    name: "应用答题页面",
    component: AppAnswerPage,
    props: true,
    meta: {
      showInMenu: false,
      access: accessEnum.USER,
    },
  },
  {
    path: "/app/result/:id",
    name: "应用结果页面",
    component: AppResultPage,
    props: true,
    meta: {
      showInMenu: false,
      access: accessEnum.USER,
    },
  },
  {
    path: "/app/my",
    name: "我的应用",
    component: MyAppPage,
    meta: {
      showInMenu: false,
      access: accessEnum.USER,
    },
  },
  {
    path: "/app/history",
    name: "应用历史",
    component: AppHistoryPage,
    meta: {
      showInMenu: false,
      access: accessEnum.USER,
    },
  },
  {
    path: "/chart",
    name: "图表分析",
    component: ChartPage,
    meta: {
      index: 3,
      showInMenu: true,
      access: accessEnum.NOT_LOGIN,
    },
  },
  /**
   * 图表历史
   */
  {
    path: "/chart/history",
    name: "图表历史",
    component: ChartHistoryPage,
    meta: {
      showInMenu: false,
      access: accessEnum.USER,
    },
  },
  /**
   * oj
   */
  {
    path: "/oj",
    name: "代码评测",
    component: OjPage,
    meta: {
      index: 3,
      showInMenu: true,
      access: accessEnum.NOT_LOGIN,
    },
  },
  /**
   * oj题目详情
   */
  {
    path: "/oj/:id",
    name: "题目详情",
    component: OjQuestionPage,
    props: true,
    meta: {
      showInMenu: false,
      access: accessEnum.NOT_LOGIN,
    },
  },
  /**
   * oj添加题目
   */
  {
    path: "/oj/question/add",
    name: "添加题目页面",
    component: OjQuestionAddPage,
    props: true,
    meta: {
      showInMenu: false,
      access: accessEnum.ADMIN,
    },
  },
  /**
   * oj修改题目
   */
  {
    path: "/oj/question/update/:id",
    name: "修改题目页面",
    component: OjQuestionAddPage,
    props: true,
    meta: {
      showInMenu: false,
      access: accessEnum.ADMIN,
    },
  },
  /**
   * codesandbox
   */
  {
    path: "/codesandbox",
    name: "代码沙箱",
    component: CodeSandboxPage,
    meta: {
      index: 4,
      showInMenu: true,
      access: accessEnum.NOT_LOGIN,
    },
  },
  /**
   * 用户相关页面
   */
  {
    path: "/user",
    name: "用户",
    component: UserLayout,
    meta: {
      showInMenu: false,
    },
    children: [
      {
        path: "/user/login",
        name: "登录",
        component: UserLoginPage,
        meta: {
          showInMenu: false,
          access: accessEnum.NOT_LOGIN,
        },
      },
      {
        path: "/user/register",
        name: "注册",
        component: UserRegisterPage,
        meta: {
          showInMenu: false,
          access: accessEnum.NOT_LOGIN,
        },
      },
      {
        path: "/user/center",
        name: "个人中心",
        component: UserCenter,
        meta: {
          showInMenu: false,
          access: accessEnum.USER,
        },
      },
    ],
  },
  /**
   * 管理员相关页面
   */
  {
    path: "/admin",
    name: "管理员页面",
    component: AdminLayout,
    meta: {
      showInMenu: false,
      access: accessEnum.ADMIN,
    },
    children: [
      {
        path: "/admin",
        name: "管理app页面",
        component: AdminPage,
        meta: {
          access: accessEnum.ADMIN,
        },
      },
    ],
  },
];

export default routes;

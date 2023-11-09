
import Vue from 'vue'
import Router from 'vue-router'

Vue.use(Router);


import OrderOrderManager from "./components/listers/OrderOrderCards"
import OrderOrderDetail from "./components/listers/OrderOrderDetail"

import StoreOrderManagementManager from "./components/listers/StoreOrderManagementCards"
import StoreOrderManagementDetail from "./components/listers/StoreOrderManagementDetail"

import PayPaymentHistoryManager from "./components/listers/PayPaymentHistoryCards"
import PayPaymentHistoryDetail from "./components/listers/PayPaymentHistoryDetail"



export default new Router({
    // mode: 'history',
    base: process.env.BASE_URL,
    routes: [
            {
                path: '/orders/orders',
                name: 'OrderOrderManager',
                component: OrderOrderManager
            },
            {
                path: '/orders/orders/:id',
                name: 'OrderOrderDetail',
                component: OrderOrderDetail
            },

            {
                path: '/stores/orderManagements',
                name: 'StoreOrderManagementManager',
                component: StoreOrderManagementManager
            },
            {
                path: '/stores/orderManagements/:id',
                name: 'StoreOrderManagementDetail',
                component: StoreOrderManagementDetail
            },

            {
                path: '/pays/paymentHistories',
                name: 'PayPaymentHistoryManager',
                component: PayPaymentHistoryManager
            },
            {
                path: '/pays/paymentHistories/:id',
                name: 'PayPaymentHistoryDetail',
                component: PayPaymentHistoryDetail
            },




    ]
})

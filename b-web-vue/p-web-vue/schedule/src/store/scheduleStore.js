/*
    专门用来存储用户信息
 */

import {defineStore} from "pinia";

/*在定义的 defineSchedule 中存储*/
export const defineSchedule = defineStore(
    "scheduleList",
    {
        state:()=>{
            return {
                itemList:[
                    {
                        sid:1,
                        uid:1,
                        title:'study java',
                        completed:0
                    },
                    {
                        sid:1,
                        uid:2,
                        title:'study python',
                        completed:0
                    },
                    {
                        sid:2,
                        uid:3,
                        title:'study php',
                        completed:1
                    }
                ]
            }
        },
        getters:{

        },
        actions:{

        }
    })
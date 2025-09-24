/*
    专门用来存储用户信息的 pinia
 */
import {defineStore} from "pinia";

/*在定义的 defineUser 中存储用户的 uid && password */
export const defineUser = defineStore(
    "loginUser",
    {
        state:()=>{
            return {
                uid:0,
                username:""
            }
        },
        getters:{

        },
        actions:{

        }
    })
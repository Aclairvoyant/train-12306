<template>
  <a-row class="login">
    <a-col :span="8" :offset="8" class="login-main">
      <h1 style="text-align: center">
        <rocket-two-tone/>&nbsp;train-12306售票系统
      </h1>
      <a-form
          :model="loginForm"
          name="basic"
          autocomplete="off"
      >
        <a-form-item
            label="手机号"
            name="mobile"
            :rules="[{ required: true, message: '请输入手机号!' }]"
        >
          <a-input v-model:value="loginForm.mobile" aria-placeholder="手机号"/>
        </a-form-item>

        <a-form-item
            label="验证码"
            name="code"
            :rules="[{ required: true, message: '请输入验证码!' }]"
        >

          <a-input v-model:value="loginForm.code">
            <template #addonAfter>
              <a @click="sendCode">获取验证码</a>
            </template>
          </a-input>

        </a-form-item>

        <a-form-item>
          <a-button type="primary" block @click="login">登录</a-button>
        </a-form-item>

      </a-form>
    </a-col>
  </a-row>

</template>
<script>
import {defineComponent, reactive} from 'vue';
import axios from 'axios';
import {useRouter} from 'vue-router';
import {notification} from "ant-design-vue";
import store from "@/store";

export default defineComponent({
  name: 'LoginView',
  setup() {

    const router = useRouter();

    const loginForm = reactive({
      mobile: '17301639133',
      code: '',
    });

    const sendCode = () => {
      axios.post("/member/member/send-code", {
        mobile: loginForm.mobile
      }).then(res => {
        let data = res.data;
        if (data.success) {
          notification.success({
                message: "发送成功",
                description: "验证码发送成功，请注意查收"
              },
              loginForm.code = "8888"
          )
        } else {
          notification.error({
            message: "发送失败",
            description: data.message
          })
        }
      })
    }

    const login = () => {
      axios.post("/member/member/login", loginForm).then(response => {
        let data = response.data;
        if (data.success) {
          notification.success({
            message: "登录成功",
            description: "欢迎回来"
          })
          router.push("/welcome");
          store.commit("setMember", data.data);
        } else {
          notification.error({
            message: "登录失败",
            description: data.message
          })
        }
      })
    }

    return {
      loginForm,
      sendCode,
      login
    };
  },
});
</script>

<style>
.login-main h1 {
  font-size: 25px;
  font-weight: bold;
}

.login-main {
  margin-top: 300px;
  padding: 30px 30px 20px;
  border: 2px solid grey;
  border-radius: 10px;
  background-color: #fcfcfc;
}
</style>

"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = common_vendor.defineComponent({
  data() {
    return {
      userInput: "",
      chatMessages: [
        new UTSJSONObject({ text: "你好！有什么我可以帮助你的吗？", type: "ai" })
      ]
      // 聊天记录
    };
  },
  methods: {
    sendMessage() {
      return common_vendor.__awaiter(this, void 0, void 0, function* () {
        if (!this.userInput.trim()) {
          common_vendor.index.showToast({ title: "请输入内容", icon: "none" });
          return Promise.resolve(null);
        }
        this.chatMessages.push({ text: this.userInput, type: "user" });
        const currentInput = this.userInput;
        this.userInput = "";
        common_vendor.index.showLoading({ title: "AI处理中..." });
        try {
          const res = yield common_vendor.index.request({
            url: "http://localhost:8080/chat",
            method: "POST",
            data: new UTSJSONObject({ userInput: currentInput }),
            header: new UTSJSONObject({ "Content-Type": "application/json" })
          });
          const response = res.data.response || "未返回结果";
          this.chatMessages.push({ text: response, type: "ai" });
        } catch (err) {
          this.chatMessages.push({ text: "发送失败，请重试", type: "error" });
        } finally {
          common_vendor.index.hideLoading();
        }
      });
    }
  }
});
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return {
    a: common_vendor.f($data.chatMessages, (message, index, i0) => {
      return {
        a: common_vendor.t(message.text),
        b: common_vendor.n(message.type),
        c: index
      };
    }),
    b: common_vendor.o((...args) => $options.sendMessage && $options.sendMessage(...args)),
    c: $data.userInput,
    d: common_vendor.o(($event) => $data.userInput = $event.detail.value),
    e: common_vendor.o((...args) => $options.sendMessage && $options.sendMessage(...args)),
    f: common_vendor.sei(_ctx.virtualHostId, "view")
  };
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-627fd07b"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/ai-chat/index.js.map

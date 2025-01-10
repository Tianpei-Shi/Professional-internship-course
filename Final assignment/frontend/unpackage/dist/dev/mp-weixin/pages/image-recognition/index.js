"use strict";
const common_vendor = require("../../common/vendor.js");
const _sfc_main = common_vendor.defineComponent({
  data() {
    return {
      imageFile: null,
      imagePreview: "",
      recognizedText: ""
      // 识别结果
    };
  },
  methods: {
    // 选择图片
    chooseImage() {
      common_vendor.index.chooseImage({
        count: 1,
        sizeType: ["compressed"],
        success: (res) => {
          this.imageFile = res.tempFilePaths[0];
          this.imagePreview = res.tempFilePaths[0];
        }
      });
    },
    // 调用后端接口识别图片
    recognizeImage() {
      return common_vendor.__awaiter(this, void 0, void 0, function* () {
        if (!this.imageFile) {
          common_vendor.index.showToast({ title: "请先上传图片", icon: "none" });
          return Promise.resolve(null);
        }
        common_vendor.index.showLoading({ title: "识别中..." });
        try {
          const res = yield common_vendor.index.request({
            url: "http://localhost:8080/image/recognize",
            method: "POST",
            filePath: this.imageFile,
            name: "image",
            header: new UTSJSONObject({ "Content-Type": "multipart/form-data" })
          });
          this.recognizedText = res.data.result || "未识别到内容";
        } catch (err) {
          common_vendor.index.showToast({ title: "识别失败，请重试", icon: "none" });
        } finally {
          common_vendor.index.hideLoading();
        }
      });
    },
    // 清空图片和结果
    clearImage() {
      this.imageFile = null;
      this.imagePreview = "";
      this.recognizedText = "";
    },
    // 跳转到历史记录页面
    viewHistory() {
      common_vendor.index.navigateTo({ url: "/pages/history/index" });
    }
  }
});
function _sfc_render(_ctx, _cache, $props, $setup, $data, $options) {
  return common_vendor.e({
    a: $data.imagePreview
  }, $data.imagePreview ? {
    b: $data.imagePreview
  } : {}, {
    c: common_vendor.o((...args) => $options.chooseImage && $options.chooseImage(...args)),
    d: common_vendor.o((...args) => $options.recognizeImage && $options.recognizeImage(...args)),
    e: !$data.imageFile,
    f: common_vendor.o((...args) => $options.clearImage && $options.clearImage(...args)),
    g: !$data.imageFile,
    h: common_vendor.o((...args) => $options.viewHistory && $options.viewHistory(...args)),
    i: $data.recognizedText
  }, $data.recognizedText ? {
    j: $data.recognizedText,
    k: common_vendor.o(($event) => $data.recognizedText = $event.detail.value)
  } : {}, {
    l: common_vendor.sei(_ctx.virtualHostId, "view")
  });
}
const MiniProgramPage = /* @__PURE__ */ common_vendor._export_sfc(_sfc_main, [["render", _sfc_render], ["__scopeId", "data-v-0dccbede"]]);
wx.createPage(MiniProgramPage);
//# sourceMappingURL=../../../.sourcemap/mp-weixin/pages/image-recognition/index.js.map

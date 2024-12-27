<template>
  <div id="app">
    <h1>Stable Diffusion 文生图生成器</h1>
    <form @submit.prevent="generateImage">
      <label>提示词:</label>
      <input v-model="prompt" required style="width: 300px; height: 30px;" />
      <label>步数:</label>
      <input type="number" v-model="steps" />
      <label>CFG Scale:</label>
      <input type="number" v-model="cfgScale" />
      <label>宽度:</label>
      <input type="number" v-model="width" />
      <label>高度:</label>
      <input type="number" v-model="height" />
      <button type="submit">生成图片</button>
    </form>
    <div v-if="image">
      <h2>生成结果:</h2>
      <img :src="'data:image/png;base64,' + image" alt="生成的图片" />
    </div>
  </div>
</template>

<script>
import axios from "axios";

export default {
  data() {
    return {
      prompt: "",
      steps: 20,
      cfgScale: 7,
      width: 512,
      height: 512,
      image: null,
    };
  },
  methods: {
    async generateImage() {
      try {
        const response = await axios.post("http://localhost:8080/api/v1/stablediffusion/generate", {
          prompt: this.prompt,
          steps: this.steps,
          cfgScale: this.cfgScale,
          width: this.width,
          height: this.height,
        });
        this.image = response.data.base64Image;
      } catch (error) {
        console.error("生成图片失败", error);
      }
    },
  },
};
</script>
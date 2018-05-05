# 3D_PVRD_platform

3D全景创业资源对接平台(3D_panoramic_venture_resources_docking_platform)

PC_management: 后台管理代码

WX_client: WX小程序端代码

server: node后台代码

## PC_management

## WX_client

### wepy框架

微信小程序端代码待用wepy框架，测试运行需要先npm安装wepy-cli，运行

``` js
wepy build --watch
```

生成dist文件夹后，可通过微信开发者工具预览

### wepy-zanui

微信小程序端UI组件采用wepy-zanui组件库

## issuse

* image标签可以加载本地图片，但背景图片用网络图片或者base64
* 组件props无法传数组，在组件中拼接，希望后台可以把类似涉及行业这种拼接好直接返回字符串
* 微信获取来的头像可能需要存在本地一下，方便评论头像显示
* 申请投资，需要和后台沟通需要什么字段
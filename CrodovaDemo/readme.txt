在Windows上搭建Crodova开发环境及打包APK

一.配置Android环境

二.配置node.js
1.进入node.js官网,下载node.js
2.进行安装
3.配置node.js的环境变量:设置环境变量名:NODE_PATH;值:node_modules路径
4.测试node.js是否安装成功,运行cmd,输入node -v查看版本号.

三.安装Cordova
1.打开命令提示符,输入npm install -g cordova;以全局方式安装Cordova
2.测试是否成功,输入cordova -v查看版本号

四:生成项目
1.输入命令:cordova create 即将创建好生成的文件夹名 com.jiaop.xxx 工程名
2.成功后,输入cd命令进入项目中
3.命令输入:cordova platform add android添加Android平台

五:导入进Android Studio
1.选择Import project-->next,导入后将html等文件放入www文件夹
2.运行测试

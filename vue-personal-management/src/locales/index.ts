import { createI18n } from 'vue-i18n'
import enUS from './messages/en-US.json'
import zhCN from './messages/zh-CN.json'

type MessageSchema = typeof zhCN

// 获取存储的语言或浏览器默认语言
const getInitialLocale = (): 'zh-CN' | 'en-US' => {
  const savedLang = sessionStorage.getItem('user-lang') as 'zh-CN' | 'en-US' | null
  if (savedLang) return savedLang
  return 'zh-CN'
}

const i18n = createI18n<[MessageSchema], 'zh-CN' | 'en-US'>({
  legacy: false,
  locale: getInitialLocale(), // 使用存储或检测的语言
  fallbackLocale: 'zh-CN',
  messages: {
    'en-US': enUS,
    'zh-CN': zhCN
  },
})

export default i18n
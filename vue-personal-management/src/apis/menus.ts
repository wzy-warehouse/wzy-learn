import type MenuItemType from '@/types/MenuItemType'
import httpInstance from '@/utils/http'

/**
 * 获取菜单列表接口
 */
export async function getLists(): Promise<MenuItemType[]> {
  const res = await httpInstance.post('/menus/lists')
  return res.data.data
}
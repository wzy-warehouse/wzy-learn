export default interface MenuItemType {
    id: number;
    path: string;
    component: string;
    menuName: string;
    name: string;
    icon: string;
    children?: MenuItemType[];
}
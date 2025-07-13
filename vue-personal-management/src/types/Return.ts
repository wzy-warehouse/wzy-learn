export default interface ReturnType {
    code: number,
    message: string,
    data: any,
    timestamp: number,
    extra: Map<string, Object>
}
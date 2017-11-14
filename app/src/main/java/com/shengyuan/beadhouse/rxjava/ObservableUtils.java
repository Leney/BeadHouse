package com.shengyuan.beadhouse.rxjava;

/**
 * Created by ${xingen} on 2017/11/6.
 * <p>
 * 一个工具类，构建各种Observable对象
 */

public class ObservableUtils {
//    /**
//     * 构建保存信息的Observable
//     * @param loginResult
//     * @return
//     */
//    public static Observable<Boolean> createSavePersonMSG(LoginResult loginResult) {
//        return Observable.create(subscriber -> {
//            try {
//                BriteDatabase briteDatabase = SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase();
//                briteDatabase.delete(Data.TABLE_USER_INFO, null,new String[]{});
//                briteDatabase.insert(Data.TABLE_USER_INFO, TransformHelper.toContentValues(loginResult));
//                subscriber.onNext(true);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        });
//    }
//
//    /**
//     * token过期，清空数据
//     * @return
//     */
//    public static Observable<Boolean> createDeletePersonMSG() {
//        return Observable.create(subscriber -> {
//            try {
//                BriteDatabase briteDatabase = SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase();
//                briteDatabase.delete(Data.TABLE_USER_INFO, null,new String[]{});
//                subscriber.onNext(true);
//            }catch (Exception e){
//                e.printStackTrace();
//            }
//        });
//    }
//
//    /**
//     * 查询数据库，获取Token
//     * @return
//     */
//    public static Observable<String> createQueryToken(){
//        return Observable.create(subscriber -> {
//            String token;
//            Cursor cursor=null;
//            try {
//                cursor= SQLBriteProvider.getInstance(ZkApplication.getInstance()).getBriteDatabase().query(createQuerySQL(),new String[]{});
//                if (cursor!=null &&cursor.moveToFirst()){
//                    token=cursor.getString(cursor.getColumnIndex(Data.COLUMNS_USER_TOKEN));
//                }else{
//                    token="";
//                }
//                subscriber.onNext(token);
//            }catch (Exception e){
//                e.printStackTrace();
//            }finally {
//                if (cursor!=null){
//                    cursor.close();
//                }
//            }
//        });
//    }
}

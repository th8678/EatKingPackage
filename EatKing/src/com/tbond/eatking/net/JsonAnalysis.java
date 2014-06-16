package com.tbond.eatking.net;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.UnknownServiceException;
import java.security.PublicKey;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.UserTokenHandler;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Comment;

import android.R.integer;
import android.hardware.Camera.Face;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.tbond.eatking.model.*;
import com.tbond.eatking.view.MainActivity;

public class JsonAnalysis {
	private static JsonAnalysis instance = new JsonAnalysis();

	RequestParams params;

	public JsonAnalysis() {

	}

	public static JsonAnalysis getInstance() {
		return instance;
	}

	/**
	 * 登陆
	 * 
	 * @param object
	 * @param userName
	 * @param userPwd
	 */
	public void login(Object object, String userName, String userPwd) {
		params = new RequestParams();
		params.put("userName", userName);
		params.put("userPwd", userPwd);
		Api.getInstance().login(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				////((MainActivity) object).setMessage(errorMess);
			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);

				Log.i("JsonAnalysis login", arg1.toString());
				try {
					String error = arg1.getString("error");// 获取pet对象的参数
					if (Integer.valueOf(error) == 1) {
						if (object instanceof MainActivity) {
							ErrorMessage errorMess = new ErrorMessage();
							String result = arg1.getString("result");
							errorMess.setError(error);
							errorMess.setResult(result);
							////((MainActivity) object).setMessage(errorMess);
						}
					} else {
						if (object instanceof MainActivity) {
							User user = new User();
							String userName = arg1.getString("userName");
							// String userPwd = arg1.getString("userPwd");
							// String image = arg1.getString("image");
							String userIntro = arg1.getString("userIntro");
							String userAvatar = arg1.getString("userAvatar");
							// String userId = arg1.getString("userId");
							// user.setImage(image);
							user.setUserName(userName);
							// user.setUserPwd(userPwd);
							user.setUserAvatar(userAvatar);
							user.setUserIntro(userIntro);
							// user.setUserId(userId);
							////((MainActivity) object).setUser(user);
						}

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.setObject(object));

	}

	/**
	 * 新用户注册
	 * 
	 * @param object
	 * @param userName
	 * @param userPwd
	 */
	public void regis(Object object, String userName, String userPwd) {
		params = new RequestParams();
		params.put("userName", userName);
		params.put("userPwd", userPwd);
		Api.getInstance().regis(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				////((MainActivity) object).setMessage(errorMess);
			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);
				Log.i("JsonAnalysis login", arg1.toString());
				try {
					String error = arg1.getString("error");
					if (Integer.valueOf(error) == 1) {
						if (object instanceof MainActivity) {
							ErrorMessage errorMess = new ErrorMessage();
							String result = arg1.getString("result");
							errorMess.setError(error);
							errorMess.setResult(result);
							////((MainActivity) object).setMessage(errorMess);
						}
					} else {
						if (object instanceof MainActivity) {
							ErrorMessage errorMess = new ErrorMessage();
							errorMess.setError(error);
							errorMess.setResult("注册成功");
							User user = new User();

							String userId = arg1.getString("userId");
							user.setUserId(userId);
							////((MainActivity) object).setMessage2(errorMess,userId);
						}

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.setObject(object));

	}

	/**
	 * 注销用户
	 * 
	 * @param object
	 */
	public void logout(Object object) {
		Api.getInstance().logout(new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				//((MainActivity) object).setMessage(errorMess);

			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);
				Log.i("JsonAnalysis login", arg1.toString());
				try {

					if (object instanceof MainActivity) {
						ErrorMessage errorMess = new ErrorMessage();
						String error = arg1.getString("error");
						String result = arg1.getString("result");
						errorMess.setError(error);
						errorMess.setResult(result);
						//((MainActivity) object).setMessage(errorMess);
					}

				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.setObject(object));
	}

	/**
	 * 收藏店铺
	 * 
	 * @param object
	 * @param shopId
	 */
	public void collectionShop(Object object, String shopId) {
		params = new RequestParams();
		params.put("shopId", shopId);
		Api.getInstance().collectionShop(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				//((MainActivity) object).setMessage(errorMess);

			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);

				Log.i("JsonAnalysis login", arg1.toString());
				try {

					if (object instanceof MainActivity) {
						ErrorMessage errorMess = new ErrorMessage();
						String error = arg1.getString("error");// 获取pet对象的参数
						String result = arg1.getString("result");
						errorMess.setError(error);
						errorMess.setResult(result);
						//((MainActivity) object).setMessage(errorMess);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.setObject(object));
	}

	/**
	 * 取消收藏店铺
	 * 
	 * @param object
	 * @param shopId
	 */
	public void cancelCollectionShop(Object object, String shopId) {
		params = new RequestParams();
		params.put("shopId", shopId);
		Api.getInstance().cancelCollectionShop(params,
				new JsonHttpResponseHandler() {
					Object object = null;

					JsonHttpResponseHandler setObject(Object object) {
						this.object = object;
						return this;
					}

					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onFailure(arg0, arg1);
						ErrorMessage errorMess = new ErrorMessage();
						errorMess.setError("1");
						errorMess.setResult("服务器繁忙");
						//((MainActivity) object).setMessage(errorMess);

					}

					@Override
					public void onSuccess(JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg1);

						Log.i("JsonAnalysis login", arg1.toString());
						try {

							if (object instanceof MainActivity) {
								ErrorMessage errorMess = new ErrorMessage();
								String error = arg1.getString("error");// 获取pet对象的参数
								String result = arg1.getString("result");
								errorMess.setError(error);
								errorMess.setResult(result);
								//((MainActivity) object).setMessage(errorMess);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}.setObject(object));
	}

	/**
	 * 创建新店铺
	 * 
	 * @param object
	 * @param shopName
	 * @param locationX
	 * @param locationY
	 * @param address
	 * @param phoneNumber
	 * @param businessTime
	 * @param tips
	 * @param state
	 */
	public void addShop(Object object, String shopName, double locationX,
			double locationY, String address, String phoneNumber,
			String businessTime, String tips, String state) {
		params = new RequestParams();
		params.put("shopName", shopName);
		params.put("locationX", String.valueOf(locationX));
		params.put("locationY", String.valueOf(locationY));
		params.put("address", address);
		params.put("phoneNumber", phoneNumber);
		params.put("businessTime", businessTime);
		params.put("tips", tips);
		params.put("state", state);
		Api.getInstance().addShop(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				//((MainActivity) object).setMessage(errorMess);

			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);
				Log.i("JsonAnalysis login", arg1.toString());
				try {
					String error = arg1.getString("error");
					if (Integer.valueOf(error) == 1) {
						if (object instanceof MainActivity) {
							ErrorMessage errorMess = new ErrorMessage();
							String result = arg1.getString("result");
							errorMess.setError(error);
							errorMess.setResult(result);
							//((MainActivity) object).setMessage(errorMess);
						}
					} else {
						if (object instanceof MainActivity) {
							Shop shop = new Shop();
							String address = arg1.getString("address");
							String shopId = arg1.getString("shopId");
							shop.setAddress(address);
							shop.setShopId(shopId);
							ErrorMessage errorMess = new ErrorMessage();
							errorMess.setError(error);
							errorMess.setResult("店铺添加成功");
							//((MainActivity) object).setMessage(errorMess);
							// //((MainActivity) object).setShop(shop);

						}

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.setObject(object));

	}

	/**
	 * 创建菜品
	 * 
	 * @param object
	 * @param shopId
	 * @param dishName
	 * @param dishPrice
	 */
	public void addDish(Object object, String shopId, String dishName,
			String dishPrice) {
		params = new RequestParams();
		params.put("shopId", shopId);
		params.put("dishName", dishName);
		params.put("dishPrice", dishPrice);
		Api.getInstance().addDish(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				//((MainActivity) object).setMessage(errorMess);

			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);
				Log.i("JsonAnalysis login", arg1.toString());
				try {
					String error = arg1.getString("error");
					if (Integer.valueOf(error) == 1) {
						if (object instanceof MainActivity) {
							ErrorMessage errorMess = new ErrorMessage();
							String result = arg1.getString("result");
							errorMess.setError(error);
							errorMess.setResult(result);
							//((MainActivity) object).setMessage(errorMess);
						}
					} else {
						if (object instanceof MainActivity) {
							ErrorMessage errorMess = new ErrorMessage();
							errorMess.setError(error);
							errorMess.setResult("菜品添加成功");
							String dishId1 = arg1.getString("dishId");
							List<Dish> dishes = new ArrayList<Dish>();
							JSONArray jsonArr = arg1.getJSONArray("dish");
							for (int i = 0; i < jsonArr.length(); i++) {
								JSONObject temp = jsonArr.getJSONObject(i);
								String dishPrice = temp.getString("dishPrice");
								String dishName = temp.getString("dishName");
								String dishImage = temp.getString("dishImage");
								String dishId = temp.getString("dishId");
								Dish dish = new Dish();
								dish.setDishId(dishId);
								dish.setDishImage(dishImage);
								dish.setDishName(dishName);
								dish.setDishPrice(dishPrice);
								dishes.add(dish);
							}
							// Dish dish = new Dish();
							// String userId = arg1.getString("userId");
							// user.setUserId(userId);
							//((MainActivity) object).setDish(dishes);
						}

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.setObject(object));

	}

	/**
	 * 签到
	 * 
	 * @param object
	 * @param shopId
	 */
	public void signin(Object object, String shopId) {
		params = new RequestParams();
		params.put("shopId", shopId);
		Api.getInstance().signin(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				//((MainActivity) object).setMessage(errorMess);

			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);

				Log.i("JsonAnalysis login", arg1.toString());
				try {

					if (object instanceof MainActivity) {
						ErrorMessage errorMess = new ErrorMessage();
						String error = arg1.getString("error");// 获取pet对象的参数
						String result = arg1.getString("result");
						errorMess.setError(error);
						errorMess.setResult(result);
						//((MainActivity) object).setMessage(errorMess);
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.setObject(object));
	}

	/**
	 * 取消菜品评价
	 * 
	 * @param object
	 * @param dishId
	 */
	public void deleteDishEvaluation(Object object, String dishId) {
		params = new RequestParams();
		params.put("dishId", dishId);
		Api.getInstance().deleteDishEvaluation(params,
				new JsonHttpResponseHandler() {
					Object object = null;

					JsonHttpResponseHandler setObject(Object object) {
						this.object = object;
						return this;
					}

					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onFailure(arg0, arg1);
						ErrorMessage errorMess = new ErrorMessage();
						errorMess.setError("1");
						errorMess.setResult("服务器繁忙");
						//((MainActivity) object).setMessage(errorMess);

					}

					@Override
					public void onSuccess(JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg1);

						Log.i("JsonAnalysis login", arg1.toString());
						try {

							if (object instanceof MainActivity) {
								ErrorMessage errorMess = new ErrorMessage();
								String error = arg1.getString("error");// 获取pet对象的参数
								String result = arg1.getString("result");
								errorMess.setError(error);
								errorMess.setResult(result);
								//((MainActivity) object).setMessage(errorMess);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}.setObject(object));
	}

	/**
	 * 添加菜品评价
	 * 
	 * @param object
	 * @param dishId
	 */
	public void addDishEvaluation(Object object, String dishId) {
		params = new RequestParams();
		params.put("dishId", dishId);
		Api.getInstance().addDishEvaluation(params,
				new JsonHttpResponseHandler() {
					Object object = null;

					JsonHttpResponseHandler setObject(Object object) {
						this.object = object;
						return this;
					}

					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onFailure(arg0, arg1);
						ErrorMessage errorMess = new ErrorMessage();
						errorMess.setError("1");
						errorMess.setResult("服务器繁忙");
						//((MainActivity) object).setMessage(errorMess);

					}

					@Override
					public void onSuccess(JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg1);

						Log.i("JsonAnalysis login", arg1.toString());
						try {

							if (object instanceof MainActivity) {
								ErrorMessage errorMess = new ErrorMessage();
								String error = arg1.getString("error");// 获取pet对象的参数
								String result = arg1.getString("result");
								errorMess.setError(error);
								errorMess.setResult(result);
								//((MainActivity) object).setMessage(errorMess);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}.setObject(object));
	}

	/**
	 * 取消对店铺评价的评价
	 * 
	 * @param object
	 * @param shopEvaluationId
	 */
	public void deleteShopEvaluationEvaluation(Object object,
			String shopEvaluationId) {
		params = new RequestParams();
		params.put("shopEvaluationId", shopEvaluationId);
		Api.getInstance().deleteShopEvaluationEvaluation(params,
				new JsonHttpResponseHandler() {
					Object object = null;

					JsonHttpResponseHandler setObject(Object object) {
						this.object = object;
						return this;
					}

					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onFailure(arg0, arg1);
						ErrorMessage errorMess = new ErrorMessage();
						errorMess.setError("1");
						errorMess.setResult("服务器繁忙");
						//((MainActivity) object).setMessage(errorMess);

					}

					@Override
					public void onSuccess(JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg1);

						Log.i("JsonAnalysis login", arg1.toString());
						try {

							if (object instanceof MainActivity) {
								ErrorMessage errorMess = new ErrorMessage();
								String error = arg1.getString("error");// 获取pet对象的参数
								String result = arg1.getString("result");
								errorMess.setError(error);
								errorMess.setResult(result);
								//((MainActivity) object).setMessage(errorMess);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}.setObject(object));
	}

	/**
	 * 添加对店铺评价的评价
	 * 
	 * @param object
	 * @param shopEvaluationId
	 */
	public void addShopEvaluationEvaluation(Object object,
			String shopEvaluationId) {
		params = new RequestParams();
		params.put("shopEvaluationId", shopEvaluationId);
		Api.getInstance().addShopEvaluationEvaluation(params,
				new JsonHttpResponseHandler() {
					Object object = null;

					JsonHttpResponseHandler setObject(Object object) {
						this.object = object;
						return this;
					}

					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onFailure(arg0, arg1);
						ErrorMessage errorMess = new ErrorMessage();
						errorMess.setError("1");
						errorMess.setResult("服务器繁忙");
						//((MainActivity) object).setMessage(errorMess);

					}

					@Override
					public void onSuccess(JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg1);

						Log.i("JsonAnalysis login", arg1.toString());
						try {

							if (object instanceof MainActivity) {
								ErrorMessage errorMess = new ErrorMessage();
								String error = arg1.getString("error");// 获取pet对象的参数
								String result = arg1.getString("result");
								errorMess.setError(error);
								errorMess.setResult(result);
								//((MainActivity) object).setMessage(errorMess);
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}.setObject(object));
	}

	/**
	 * 举报店铺信息
	 * 
	 * @param object
	 * @param shopId
	 * @param shopName
	 * @param locationX
	 * @param locationY
	 * @param phoneNumber
	 * @param state
	 * @param businessTime
	 * @param comment
	 */
	public void addShopReport(Object object, String shopId, String shopName,
			double locationX, double locationY, String phoneNumber,
			String state, String businessTime, String comment) {
		params = new RequestParams();
		params.put("shopId", shopId);
		params.put("shopName", shopName);
		params.put("locationX", String.valueOf(locationX));
		params.put("locationY", String.valueOf(locationY));
		params.put("phoneNumber", phoneNumber);
		params.put("state", state);
		params.put("businessTime", businessTime);
		params.put("comment", comment);

		Api.getInstance().addShopReport(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				//((MainActivity) object).setMessage(errorMess);

			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);

				Log.i("JsonAnalysis login", arg1.toString());
				try {

					if (object instanceof MainActivity) {
						ErrorMessage errorMess = new ErrorMessage();
						String error = arg1.getString("error");// 获取pet对象的参数
						if (Integer.valueOf(error) == 1) {
							String result = arg1.getString("result");
							errorMess.setError(error);
							errorMess.setResult(result);
							//((MainActivity) object).setMessage(errorMess);
						} else {
							String reportId = arg1.getString("reportId");
							errorMess.setError(error);
							errorMess.setResult("成功举报");
							//((MainActivity) object).setMessage2(errorMess,reportId);
						}

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.setObject(object));
	}

	/**
	 * 举报菜信息
	 * 
	 * @param dishId
	 * @param comment
	 * @param reponseHandler
	 */
	public void addDishReport(Object object, String dishId, String comment) {
		params = new RequestParams();
		params.put("dishId", dishId);
		params.put("comment", comment);

		Api.getInstance().addDishReport(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				//((MainActivity) object).setMessage(errorMess);

			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);

				Log.i("JsonAnalysis login", arg1.toString());
				try {

					if (object instanceof MainActivity) {
						ErrorMessage errorMess = new ErrorMessage();
						String error = arg1.getString("error");// 获取pet对象的参数
						if (Integer.valueOf(error) == 1) {
							String result = arg1.getString("result");
							errorMess.setError(error);
							errorMess.setResult(result);
							//((MainActivity) object).setMessage(errorMess);
						} else {
							String reportId = arg1.getString("reportId");
							errorMess.setError(error);
							errorMess.setResult("成功举报");
							//((MainActivity) object).setMessage2(errorMess,reportId);
						}

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.setObject(object));

	}

	/**
	 * 通过信息搜索店铺
	 * 
	 * @param object
	 * @param key
	 */

	public void searchShopByKey(Object object, String key) {
		//params = new RequestParams();
		//params.put("key", key);

		Api.getInstance().searchShopByKey(params,
				new JsonHttpResponseHandler() {
					Object object = null;

					JsonHttpResponseHandler setObject(Object object) {
						this.object = object;
						return this;
					}

					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onFailure(arg0, arg1);
						ErrorMessage errorMess = new ErrorMessage();
						errorMess.setError("1");
						errorMess.setResult("服务器繁忙");
						//((MainActivity) object).setMessage(errorMess);

					}

					@Override
					public void onSuccess(JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg1);

						Log.i("JsonAnalysis login", arg1.toString());
//						try {
//
//							if (object instanceof MainActivity) {
//								ErrorMessage errorMess = new ErrorMessage();
//								String error = arg1.getString("error");// 获取pet对象的参数
//								if (Integer.valueOf(error) == 1) {
//									String result = arg1.getString("result");
//									errorMess.setError(error);
//									errorMess.setResult(result);
//									((MainActivity) object)
//											.setMessage(errorMess);
//								} else {
//									String shopId = arg1.getString("shopId");
//									String userId = arg1.getString("userId");
//									String shopName = arg1
//											.getString("shopName");
//									String locationX = arg1
//											.getString("locationX");
//									String locationY = arg1
//											.getString("locationY");
//									String address = arg1.getString("address");
//									String phoneNumber = arg1
//											.getString("phoneNumber");
//									String businessTime = arg1
//											.getString("businessTime");
//									String tips = arg1.getString("tips");
//									String state = arg1.getString("state");
//									Shop shop = new Shop();
//									shop.setAddress(address);
//									shop.setBusinessTime(businessTime);
//									shop.setLocationX(locationX);
//									shop.setLocationY(locationY);
//									shop.setphoneNumber(phoneNumber);
//									shop.setShopId(shopId);
//									shop.setShopName(shopName);
//									shop.setState(state);
//									shop.setTips(tips);
//									shop.setUserId(userId);
//									//((MainActivity) object).setShop(shop);
//								}
//
//							}
//						} catch (JSONException e) {
//							// TODO Auto-generated catch block
//							e.printStackTrace();
//						}

					}
				}.setObject(object));
	}

	/**
	 * id搜索商户
	 * 
	 * @param object
	 * @param shopId
	 */
	public void searchShopById(Object object, String shopId) {
		params = new RequestParams();
		params.put("shopId", shopId);

		Api.getInstance().searchShopById(params, new JsonHttpResponseHandler() {
			Object object = null;

			JsonHttpResponseHandler setObject(Object object) {
				this.object = object;
				return this;
			}

			@Override
			public void onFailure(Throwable arg0, JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onFailure(arg0, arg1);
				ErrorMessage errorMess = new ErrorMessage();
				errorMess.setError("1");
				errorMess.setResult("服务器繁忙");
				//((MainActivity) object).setMessage(errorMess);

			}

			@Override
			public void onSuccess(JSONObject arg1) {
				// TODO Auto-generated method stub
				// super.onSuccess(arg1);

				Log.i("JsonAnalysis login", arg1.toString());
				try {

					if (object instanceof MainActivity) {
						// ErrorMessage errorMess = new ErrorMessage();
						// String error = arg1.getString("error");// 获取pet对象的参数
						// if(Integer.valueOf(error) == 1){
						// String result = arg1.getString("result");
						// errorMess.setError(error);
						// errorMess.setResult(result);
						// //((MainActivity) object).setMessage(errorMess);
						// }else{

						List<Dish> dishes = new ArrayList<Dish>();
						JSONArray jsonArr = arg1.getJSONArray("dish");
						for (int i = 0; i < jsonArr.length(); i++) {
							JSONObject temp = jsonArr.getJSONObject(i);
							String dishPrice = temp.getString("dishPrice");
							String userName = temp.getString("userName");
							String dishName = temp.getString("dishName");
							String dishImage = temp.getString("dishImage");

							Dish dish = new Dish();
							dish.setDishImage(dishImage);
							dish.setDishName(dishName);
							dish.setDishPrice(dishPrice);
							dishes.add(dish);
						}
						String state = arg1.getString("state");
						String businessTime = arg1.getString("businessTime");
						String locationX = arg1.getString("locationX");
						String locationY = arg1.getString("locationY");
						String phoneNumber = arg1.getString("phoneNumber");
						String address = arg1.getString("address");
						String shopName = arg1.getString("shopName");
						String shopId = arg1.getString("shopId");
						String userId = arg1.getString("userId");
						String userName = arg1.getString("userName");
						String tips = arg1.getString("tips");
						String isCollected = arg1.getString("isCollected");
						Shop shop = new Shop();
						shop.setAddress(address);
						shop.setBusinessTime(businessTime);
						shop.setLocationX(locationX);
						shop.setLocationY(locationY);
						shop.setphoneNumber(phoneNumber);
						shop.setShopId(shopId);
						shop.setShopName(shopName);
						shop.setState(state);
						shop.setTips(tips);
						shop.setUserId(userId);
						shop.setIsCollected(isCollected);
						UserComment comment = new UserComment();
						JSONObject temp = arg1.getJSONObject("comment");
						String tastGrade = temp.getString("tastGrade");
						String time = temp.getString("time");
						String shopId1 = temp.getString("shopId");
						String shopEvaluationId = temp
								.getString("shopEvaluationId");
						String userId1 = temp.getString("userId");
						String environmentGrade = temp
								.getString("environmentGrade");
						String userName1 = temp.getString("userName");
						String serviceGrade = temp.getString("serviceGrade");
						String pricePerPerson = temp
								.getString("pricePerPerson");
						String comment1 = temp.getString("comment");
						String userAvatar = temp.getString("userAvatar");
						comment.setComment(comment1);
						comment.setEnvironmentGrade(environmentGrade);
						comment.setPricePerPerson(pricePerPerson);
						comment.setServiceGrade(serviceGrade);
						comment.setShopEvaluationId(shopEvaluationId);
						comment.setShopId(shopId1);
						comment.setTastGrade(tastGrade);
						comment.setTime(time);
						comment.setUserAvatar(userAvatar);
						comment.setUserName(userName1);
						comment.setUserId(userId1);
						// //((MainActivity) object).setDish(dishes);
						//((MainActivity) object).setComment(comment);
						// //((MainActivity) object).setShop(shop);
						// }

					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}.setObject(object));
	}

	public void searchShopEvaluationById(Object object, String shopId) {
		params = new RequestParams();
		params.put("shopId", shopId);

		Api.getInstance().searchShopEvaluationById(params,
				new JsonHttpResponseHandler() {
					Object object = null;

					JsonHttpResponseHandler setObject(Object object) {
						this.object = object;
						return this;
					}

					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onFailure(arg0, arg1);
						ErrorMessage errorMess = new ErrorMessage();
						errorMess.setError("1");
						errorMess.setResult("服务器繁忙");
						//((MainActivity) object).setMessage(errorMess);

					}

					@Override
					public void onSuccess(JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg1);

						Log.i("JsonAnalysis login", arg1.toString());
						// try {
						//
						// if (object instanceof MainActivity) {
						// // ErrorMessage errorMess = new ErrorMessage();
						// // String error = arg1.getString("error");//
						// 获取pet对象的参数
						// // if(Integer.valueOf(error) == 1){
						// // String result = arg1.getString("result");
						// // errorMess.setError(error);
						// // errorMess.setResult(result);
						// // //((MainActivity) object).setMessage(errorMess);
						// // }else{
						//
						// List<Dish> dishes = new ArrayList<Dish>();
						// JSONArray jsonArr = arg1.getJSONArray("dish");
						// for (int i = 0; i < jsonArr.length(); i++) {
						// JSONObject temp = jsonArr.getJSONObject(i);
						// String dishPrice = temp.getString("dishPrice");
						// String userName = temp.getString("userName");
						// String dishName = temp.getString("dishName");
						// String dishImage = temp.getString("dishImage");
						//
						// Dish dish = new Dish();
						// dish.setDishImage(dishImage);
						// dish.setDishName(dishName);
						// dish.setDishPrice(dishPrice);
						// dishes.add(dish);
						// }
						// String state = arg1.getString("state");
						// String businessTime = arg1.getString("businessTime");
						// String locationX = arg1.getString("locationX");
						// String locationY = arg1.getString("locationY");
						// String phoneNumber = arg1.getString("phoneNumber");
						// String address = arg1.getString("address");
						// String shopName = arg1.getString("shopName");
						// String shopId = arg1.getString("shopId");
						// String userId = arg1.getString("userId");
						// String userName = arg1.getString("userName");
						// String tips = arg1.getString("tips");
						// String isCollected = arg1.getString("isCollected");
						// Shop shop = new Shop();
						// shop.setAddress(address);
						// shop.setBusinessTime(businessTime);
						// shop.setLocationX(locationX);
						// shop.setLocationY(locationY);
						// shop.setphoneNumber(phoneNumber);
						// shop.setShopId(shopId);
						// shop.setShopName(shopName);
						// shop.setState(state);
						// shop.setTips(tips);
						// shop.setUserId(userId);
						// UserComment comment = new UserComment();
						// JSONObject temp = arg1.getJSONObject("comment");
						// String tastGrade = temp.getString("tastGrade");
						// String time = temp.getString("time");
						// String shopId1 = temp.getString("shopId");
						// String shopEvaluationId =
						// temp.getString("shopEvaluationId");
						// String userId1 = temp.getString("userId");
						// String environmentGrade =
						// temp.getString("environmentGrade");
						// String userName1 = temp.getString("userName");
						// String serviceGrade = temp.getString("serviceGrade");
						// String pricePerPerson =
						// temp.getString("pricePerPerson");
						// String comment1 = temp.getString("comment");
						// String userAvatar = temp.getString("userAvatar");
						// comment.setComment(comment1);
						// comment.setEnvironmentGrade(environmentGrade);
						// comment.setPricePerPerson(pricePerPerson);
						// comment.setServiceGrade(serviceGrade);
						// comment.setShopEvaluationId(shopEvaluationId);
						// comment.setShopId(shopId1);
						// comment.setTastGrade(tastGrade);
						// comment.setTime(time);
						// comment.setUserAvatar(userAvatar);
						// comment.setUserName(userName1);
						// comment.setuUserId(userId1);
						// ////((MainActivity) object).setDish(dishes);
						// //((MainActivity) object).setComment(comment);
						// ////((MainActivity) object).setShop(shop);
						// //}
						//
						//
						// }
						// } catch (JSONException e) {
						// // TODO Auto-generated catch block
						// e.printStackTrace();
						// }

					}
				}.setObject(object));
	}
	
	 /**
		 * 添加商铺评价
		 * 
		 * @param object
		 * @param shopId
		 * @param tasteGrade
		 * @param environmentGrade
		 * @param serviceGrade
		 * @param pricePerPerson
		 * @param comment
		 
		 */
		 public void addShopEvaluation(Object object,String shopId, String tasteGrade, String
		 environmentGrade, String serviceGrade, String pricePerPerson,
		 String comment){
			 params = new RequestParams();
			 params.put("shopId", shopId);
			 params.put("tasteGrade", tasteGrade);
			 params.put("environmentGrade", environmentGrade);
			 params.put("serviceGrade", serviceGrade);
			 params.put("pricePerPerson", pricePerPerson);
			 params.put("comment", comment);

				Api.getInstance().addShopEvaluation(params,
						new JsonHttpResponseHandler() {
							Object object = null;

							JsonHttpResponseHandler setObject(Object object) {
								this.object = object;
								return this;
							}

							@Override
							public void onFailure(Throwable arg0, JSONObject arg1) {
								// TODO Auto-generated method stub
								// super.onFailure(arg0, arg1);
								ErrorMessage errorMess = new ErrorMessage();
								errorMess.setError("1");
								errorMess.setResult("服务器繁忙");
								//((MainActivity) object).setMessage(errorMess);

							}

							@Override
							public void onSuccess(JSONObject arg1) {
								// TODO Auto-generated method stub
								// super.onSuccess(arg1);

								Log.i("JsonAnalysis login", arg1.toString());
								// try {
								//
								// if (object instanceof MainActivity) {
								// // ErrorMessage errorMess = new ErrorMessage();
								// // String error = arg1.getString("error");//
								// 获取pet对象的参数
								// // if(Integer.valueOf(error) == 1){
								// // String result = arg1.getString("result");
								// // errorMess.setError(error);
								// // errorMess.setResult(result);
								// // //((MainActivity) object).setMessage(errorMess);
								// // }else{
								//
								// List<Dish> dishes = new ArrayList<Dish>();
								// JSONArray jsonArr = arg1.getJSONArray("dish");
								// for (int i = 0; i < jsonArr.length(); i++) {
								// JSONObject temp = jsonArr.getJSONObject(i);
								// String dishPrice = temp.getString("dishPrice");
								// String userName = temp.getString("userName");
								// String dishName = temp.getString("dishName");
								// String dishImage = temp.getString("dishImage");
								//
								// Dish dish = new Dish();
								// dish.setDishImage(dishImage);
								// dish.setDishName(dishName);
								// dish.setDishPrice(dishPrice);
								// dishes.add(dish);
								// }
								// String state = arg1.getString("state");
								// String businessTime = arg1.getString("businessTime");
								// String locationX = arg1.getString("locationX");
								// String locationY = arg1.getString("locationY");
								// String phoneNumber = arg1.getString("phoneNumber");
								// String address = arg1.getString("address");
								// String shopName = arg1.getString("shopName");
								// String shopId = arg1.getString("shopId");
								// String userId = arg1.getString("userId");
								// String userName = arg1.getString("userName");
								// String tips = arg1.getString("tips");
								// String isCollected = arg1.getString("isCollected");
								// Shop shop = new Shop();
								// shop.setAddress(address);
								// shop.setBusinessTime(businessTime);
								// shop.setLocationX(locationX);
								// shop.setLocationY(locationY);
								// shop.setphoneNumber(phoneNumber);
								// shop.setShopId(shopId);
								// shop.setShopName(shopName);
								// shop.setState(state);
								// shop.setTips(tips);
								// shop.setUserId(userId);
								// UserComment comment = new UserComment();
								// JSONObject temp = arg1.getJSONObject("comment");
								// String tastGrade = temp.getString("tastGrade");
								// String time = temp.getString("time");
								// String shopId1 = temp.getString("shopId");
								// String shopEvaluationId =
								// temp.getString("shopEvaluationId");
								// String userId1 = temp.getString("userId");
								// String environmentGrade =
								// temp.getString("environmentGrade");
								// String userName1 = temp.getString("userName");
								// String serviceGrade = temp.getString("serviceGrade");
								// String pricePerPerson =
								// temp.getString("pricePerPerson");
								// String comment1 = temp.getString("comment");
								// String userAvatar = temp.getString("userAvatar");
								// comment.setComment(comment1);
								// comment.setEnvironmentGrade(environmentGrade);
								// comment.setPricePerPerson(pricePerPerson);
								// comment.setServiceGrade(serviceGrade);
								// comment.setShopEvaluationId(shopEvaluationId);
								// comment.setShopId(shopId1);
								// comment.setTastGrade(tastGrade);
								// comment.setTime(time);
								// comment.setUserAvatar(userAvatar);
								// comment.setUserName(userName1);
								// comment.setuUserId(userId1);
								// ////((MainActivity) object).setDish(dishes);
								// //((MainActivity) object).setComment(comment);
								// ////((MainActivity) object).setShop(shop);
								// //}
								//
								//
								// }
								// } catch (JSONException e) {
								// // TODO Auto-generated catch block
								// e.printStackTrace();
								// }

							}
						}.setObject(object));
		 }
		 public void getAllPoint(Object object){
			 Api.getInstance().getAllPoint(new JsonHttpResponseHandler() {
					Object object = null;

					JsonHttpResponseHandler setObject(Object object) {
						this.object = object;
						return this;
					}

					@Override
					public void onFailure(Throwable arg0, JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onFailure(arg0, arg1);
						ErrorMessage errorMess = new ErrorMessage();
						errorMess.setError("1");
						errorMess.setResult("服务器繁忙");
						//((MainActivity) object).setMessage(errorMess);

					}

					@Override
					public void onSuccess(JSONObject arg1) {
						// TODO Auto-generated method stub
						// super.onSuccess(arg1);
						Log.i("JsonAnalysis login", arg1.toString());
						try {

							if (object instanceof MainActivity) {
								String myPioneerPoint = arg1.getString("myPioneerPoint");
								String myChowhoundPoint = arg1.getString("myChowhoundPoint");
								String pioneerRank = arg1.getString("pioneerRank");
								String chowhoundRank = arg1.getString("chowhoundRank");
								List<User> pioneers = new ArrayList<User>();
								List<User> chowhounds = new ArrayList<User>();
								JSONArray pArr = arg1.getJSONArray("pioneer");
								JSONArray cArr = arg1.getJSONArray("chowhound");
								for (int i = 0; i < pArr.length(); i++) {
									JSONObject temp = pArr.getJSONObject(i);
									String point = temp.getString("point");
									String userName = temp.getString("userName");
									String userAvatar = temp.getString("userAvatar");
									String userIntro = temp.getString("userIntro");
									User pioneer = new User(); 
									pioneer.setPoint(point);
									pioneer.setUserName(userName);
									pioneer.setUserAvatar(userAvatar);
									pioneer.setUserIntro(userIntro);
									pioneers.add(pioneer);
								}
								for (int i = 0; i < cArr.length(); i++) {
									JSONObject temp = cArr.getJSONObject(i);
									String point = temp.getString("point");
									String userName = temp.getString("userName");
									String userAvatar = temp.getString("userAvatar");
									String userIntro = temp.getString("userIntro");
									User chowhound = new User(); 
									chowhound.setPoint(point);
									chowhound.setUserName(userName);
									chowhound.setUserAvatar(userAvatar);
									chowhound.setUserIntro(userIntro);
									chowhounds.add(chowhound);
								}
								//((MainActivity) object).setUser(pioneers,chowhounds);
								
							}

						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}.setObject(object));			
			 }
		
		 /**
			 * 通过uid查询用户信息
			 */
			 public void getPersonPage(Object object){
				 Api.getInstance().getPersonPage(new JsonHttpResponseHandler() {
						Object object = null;

						JsonHttpResponseHandler setObject(Object object) {
							this.object = object;
							return this;
						}

						@Override
						public void onFailure(Throwable arg0, JSONObject arg1) {
							// TODO Auto-generated method stub
							// super.onFailure(arg0, arg1);
							ErrorMessage errorMess = new ErrorMessage();
							errorMess.setError("1");
							errorMess.setResult("服务器繁忙");
							//((MainActivity) object).setMessage(errorMess);

						}

						@Override
						public void onSuccess(JSONObject arg1) {
							// TODO Auto-generated method stub
							// super.onSuccess(arg1);
							Log.i("JsonAnalysis login", arg1.toString());
							try {

								if (object instanceof MainActivity) {
									String userId = arg1.getString("userId");
									String userName = arg1.getString("userName");
									String userAvatar = arg1.getString("userAvatar");
									String userIntro = arg1.getString("userIntro");
									String mypioneerpoint = arg1.getString("mypioneerpoint");
									String myChowhoundPoint = arg1.getString("myChowhoundPoint");
									String myContribute = arg1.getString("myContribute");
									User user = new User(); 
									user.setUserId(userId);
									user.setUserName(userName);
									user.setUserAvatar(userAvatar);
									user.setUserIntro(userIntro);
									user.setMypioneerpoint(mypioneerpoint);
									user.setMyContribute(myContribute);
									user.setMyChowhoundPoint(myChowhoundPoint);
									JSONObject temp = arg1.getJSONObject("shopEvaluation");
									
									
									String tastGrade = temp.getString("tastGrade");
									String environmentGrade = temp.getString("environmentGrade");
									String serviceGrade = temp.getString("serviceGrade");
									String pricePerPerson = temp.getString("pricePerPerson");
									String time = temp.getString("time");
									String comment = temp.getString("comment");
									String shopName = temp.getString("shopName");
									
									UserComment comment1 = new UserComment();
									 comment1.setComment(comment);
									 comment1.setTastGrade(tastGrade);
									 comment1.setEnvironmentGrade(environmentGrade);
									 comment1.setServiceGrade(serviceGrade);
									 comment1.setPricePerPerson(pricePerPerson);
									 comment1.setTime(time);
									 comment1.setShopName(shopName);
									//((MainActivity) object).setUser(user);
									
								}

							} catch (JSONException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

						}
					}.setObject(object));		
			 }
			 
				 /**
				 * 获得个人贡献
				 * @param reponseHandler
				 */
				 public void getPersonContribute(Object object){
					 Api.getInstance().getPersonContribute(new JsonHttpResponseHandler() {
							Object object = null;

							JsonHttpResponseHandler setObject(Object object) {
								this.object = object;
								return this;
							}

							@Override
							public void onFailure(Throwable arg0, JSONObject arg1) {
								// TODO Auto-generated method stub
								// super.onFailure(arg0, arg1);
								ErrorMessage errorMess = new ErrorMessage();
								errorMess.setError("1");
								errorMess.setResult("服务器繁忙");
								//((MainActivity) object).setMessage(errorMess);

							}

							@Override
							public void onSuccess(JSONObject arg1) {
								// TODO Auto-generated method stub
								// super.onSuccess(arg1);
								Log.i("JsonAnalysis login", arg1.toString());
								try {

									if (object instanceof MainActivity) {
										String userId = arg1.getString("userId");
										String userName = arg1.getString("userName");
										String userAvatar = arg1.getString("userAvatar");
										String userIntro = arg1.getString("userIntro");
										String mypioneerpoint = arg1.getString("mypioneerpoint");
										String myChowhoundPoint = arg1.getString("myChowhoundPoint");
										String myContribute = arg1.getString("myContribute");
										User user = new User(); 
										user.setUserId(userId);
										user.setUserName(userName);
										user.setUserAvatar(userAvatar);
										user.setUserIntro(userIntro);
										user.setMypioneerpoint(mypioneerpoint);
										user.setMyContribute(myContribute);
										user.setMyChowhoundPoint(myChowhoundPoint);
										JSONObject temp = arg1.getJSONObject("shopEvaluation");
										
										
										String tastGrade = temp.getString("tastGrade");
										String environmentGrade = temp.getString("environmentGrade");
										String serviceGrade = temp.getString("serviceGrade");
										String pricePerPerson = temp.getString("pricePerPerson");
										String time = temp.getString("time");
										String comment = temp.getString("comment");
										String shopName = temp.getString("shopName");
										
										UserComment comment1 = new UserComment();
										 comment1.setComment(comment);
										 comment1.setTastGrade(tastGrade);
										 comment1.setEnvironmentGrade(environmentGrade);
										 comment1.setServiceGrade(serviceGrade);
										 comment1.setPricePerPerson(pricePerPerson);
										 comment1.setTime(time);
										 comment1.setShopName(shopName);
										//((MainActivity) object).setUser(user);
										
									}

								} catch (JSONException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}

							}
						}.setObject(object));		
					 
				 }
				 /**
					 * 通过uid查询用户收藏店铺信息，包括离当前位置的距离
					 * @param reponseHandler
					 */
					 public void getCollection(Object object){
						 Api.getInstance().getCollection(new JsonHttpResponseHandler() {
								Object object = null;

								JsonHttpResponseHandler setObject(Object object) {
									this.object = object;
									return this;
								}

								@Override
								public void onFailure(Throwable arg0, JSONArray arg1) {
									// TODO Auto-generated method stub
									// super.onFailure(arg0, arg1);
									ErrorMessage errorMess = new ErrorMessage();
									errorMess.setError("1");
									errorMess.setResult("服务器繁忙");
									//((MainActivity) object).setMessage(errorMess);

								}

								@Override
								public void onSuccess(JSONArray arg1) {
									// TODO Auto-generated method stub
									// super.onSuccess(arg1);
									Log.i("JsonAnalysis login", arg1.toString());
									try {
										if (object instanceof MainActivity) {
											List<Shop> shops = new ArrayList<Shop>();
											for(int i = 0; i < arg1.length();i++){
												JSONObject temp = arg1.getJSONObject(i);
												String userId = temp.getString("userId");
												String time = temp.getString("time");
												String shopId = temp.getString("shopId");
												String shopName = temp.getString("shopName");
												String locationX = temp.getString("locationX");
												String locationY = temp.getString("locationY");
												String address = temp.getString("address");
												Shop shop = new Shop();
												shop.setUserId(userId);
												shop.setTime(time);
												shop.setShopName(shopName);
												shop.setLocationX(locationX);
												shop.setLocationY(locationY);
												shop.setAddress(address);
												shop.setShopId(shopId);
												shops.add(shop);
											}
											//((MainActivity) object).setShop(shops);
											
										}

									} catch (JSONException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}

								}
							}.setObject(object));		
						 
						 
					 }
					
					 /**
						 * 获得用户两项积分信息
						 * @param reponseHandler
						 */
						 public void getPersonPoint(Object object){
							 Api.getInstance().getPersonPoint(new JsonHttpResponseHandler() {
									Object object = null;

									JsonHttpResponseHandler setObject(Object object) {
										this.object = object;
										return this;
									}

									@Override
									public void onFailure(Throwable arg0, JSONObject arg1) {
										// TODO Auto-generated method stub
										// super.onFailure(arg0, arg1);
										ErrorMessage errorMess = new ErrorMessage();
										errorMess.setError("1");
										errorMess.setResult("服务器繁忙");
										//((MainActivity) object).setMessage(errorMess);

									}

									@Override
									public void onSuccess(JSONObject arg1) {
										// TODO Auto-generated method stub
										// super.onSuccess(arg1);
										Log.i("JsonAnalysis login", arg1.toString());
										try {
											if (object instanceof MainActivity) {
												
												User user = new User();
													JSONObject pioneer = arg1.getJSONObject("pioneer");
													JSONObject chowhound = arg1.getJSONObject("chowhound");
													
													String userId = pioneer.getString("userId");
													String userName = pioneer.getString("userName");
													String userAvatar = pioneer.getString("userAvatar");
													String userIntro = pioneer.getString("userIntro");
													String findShopScore = pioneer.getString("findShopScore");
													String findDishScore = pioneer.getString("findDishScore");
													String otherSignShopScore = pioneer.getString("signShopScore");
													String shopHighEvaluateScore = pioneer.getString("shopHighEvaluateScore");
													String dishHighEvaluateScore = pioneer.getString("dishHighEvaluateScore");
													String pioneerRank = pioneer.getString("rank");
													user.setUserId(userId);
													user.setUserName(userName);
													user.setUserAvatar(userAvatar);
													user.setUserIntro(userIntro);
													user.setFindShopScore(findShopScore);
													user.setFindDishScore(findDishScore);
													user.setOtherSignShopScore(otherSignShopScore);
													user.setShopHighEvaluateScore(shopHighEvaluateScore);
													user.setDishHighEvaluateScore(dishHighEvaluateScore);
													user.setPioneerRank(pioneerRank);
												
													String ownSignShopScore = chowhound.getString("signShopScore");
													String evaluateShopScore = chowhound.getString("evaluateShopScore");
													String evaluateShopCommentScore = chowhound.getString("evaluateShopCommentScore");
													
													String evaluateCommentGainScore = chowhound.getString("evaluateCommentGainScore");
													String evaluationDishScore = chowhound.getString("evaluationDishScore");
													String correctInfoPassScore = chowhound.getString("correctInfoPassScore");
													String chowhoundRank = chowhound.getString("rank");
													user.setOwnSignShopScore(ownSignShopScore);
													user.setEvaluateShopScore(evaluateShopScore);
										            user.setEvaluateShopCommentScore(evaluateShopCommentScore);
										            user.setEvaluateCommentGainScore(evaluateCommentGainScore);
										            user.setEvaluationDishScore(evaluationDishScore);
										            user.setCorrectInfoPassScore(correctInfoPassScore);
										            user.setChowhoundRank(chowhoundRank);
												//((MainActivity) object).setUser(user);
												
											}

										} catch (JSONException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}

									}
								}.setObject(object));		
							 
						 }
						 /**
							 * 查询我的评价
							 * @param reponseHandler
							 */
							 public void getPersonComment(Object object){
								 Api.getInstance().getPersonComment(new JsonHttpResponseHandler() {
										Object object = null;

										JsonHttpResponseHandler setObject(Object object) {
											this.object = object;
											return this;
										}

										@Override
										public void onFailure(Throwable arg0, JSONObject arg1) {
											// TODO Auto-generated method stub
											// super.onFailure(arg0, arg1);
											ErrorMessage errorMess = new ErrorMessage();
											errorMess.setError("1");
											errorMess.setResult("服务器繁忙");
											//((MainActivity) object).setMessage(errorMess);

										}

										@Override
										public void onSuccess(JSONObject arg1) {
											// TODO Auto-generated method stub
											// super.onSuccess(arg1);
											Log.i("JsonAnalysis login", arg1.toString());
											try {
												if (object instanceof MainActivity) {
													JSONArray comments = arg1.getJSONArray("comment");
													//JSONArray dishes = arg1.getJSONArray("dish");
													List<UserComment> userComments = new ArrayList<UserComment>();
													for(int i = 0 ; i < comments.length(); i++){
														
														JSONObject temp = comments.getJSONObject(i);
													String shopName = temp.getString("shopName");
													String tastGrade = temp.getString("tastGrade");
													String environmentGrade = temp.getString("environmentGrade");
													String serviceGrade = temp.getString("serviceGrade");
													String pricePerPerson = temp.getString("pricePerPerson");
													String time = temp.getString("time");
													String comment = temp.getString("comment");
													UserComment tempComment = new UserComment();
													tempComment.setShopName(shopName);
													tempComment.setTastGrade(tastGrade);
													tempComment.setEnvironmentGrade(environmentGrade);
													tempComment.setServiceGrade(serviceGrade);
													tempComment.setPricePerPerson(pricePerPerson);
													tempComment.setTime(time);
													tempComment.setComment(comment);
													userComments.add(tempComment);
													}
//													List<Dish> myDishes = new ArrayList<Dish>();
//													for(int i = 0; i < dishes.length(); i++){
//														JSONObject temp = dishes.getJSONObject(i);
//														String dishImage = temp.getString("dishImage");
//														String dishName = temp.getString("dishName");
//														String dishPrice = temp.getString("dishPrice");
//														Dish dish = new Dish();
//														dish.setDishImage(dishImage);
//														dish.setDishName(dishName);
//														dish.setDishPrice(dishPrice);
//													}
														
														
														
													//((MainActivity) object).setComment(userComments);
													
												}

											} catch (JSONException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}

										}
									}.setObject(object));		

							 }
																			 
		 // /**
	// * 通过中心位置获得radius半径内所有商店信息
	// * @param locX
	// * @param locY
	// * @param radius
	// * @param reponseHandler
	// */
	// public void searchShopByLoc(double locX, double locY, double radius,
	// JsonHttpResponseHandler reponseHandler){
	//
	// }
	//
	
	
	

	
	//
	// /**
	// * 对店铺评价点赞或者取消赞
	// * @param seId
	// * @param isPrase true:点赞 false：取消赞
	// * @param reponseHandler
	// */
	// public void praseShopComment(String seId, boolean isPrase,
	// JsonHttpResponseHandler reponseHandler){
	// //addShopEvaluation deleteShopEvaluation
	//
	// }
	//
	// /**
	// * 对菜点赞或者取消赞
	// * @param dishId
	// * @param isPrase true:点赞 false：取消赞
	// * @param reponseHandler
	// */
	// public void praseDish(String dishId, boolean isPrase,
	// JsonHttpResponseHandler reponseHandler){
	// //addDishEvaluation deleteDishEvaluation
	//
	// }
	//
	
	
	// /**
	// * 获得两个排行榜名次榜，前10名
	// * @param reponseHandler
	// */
	// public void getLevelList(JsonHttpResponseHandler reponseHandler){
	//
	// }
}

export default class NetUtil {

  static postJson(url, data) {
    return NetUtil.requestJson(url, data, "post");
  }

  static getJson(url, data) {
    return NetUtil.requestJson(url, data, "get");
  }

  static requestJson(url, data, method) {
    data = data || {};
    return new Promise(function (resolve, reject) {
      wx.request({
        "method": method,
        "url": url,
        "data": data,
        "header": {
          'Content-Type': 'application/json'
        },
        success: function (res) {
          resolve(res);
        },
        fail: function (err) {
          reject(err);
        }
      })
    });
  }
}

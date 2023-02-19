import axios from "axios";
import { useContext, useEffect, useState } from "react";
import { AuthContext } from "../App";

const API_STATUS = {
  loading: "loading",
  success: "success",
  errored: "errored",
};
export const METHODS = {
  GET: "get",
  POST: "post",
};
function useAPIHandler(url, method) {
  const [data, setData] = useState(null);
  const [status, setStatus] = useState(API_STATUS.loading);
  const { isAuthenticated, token} = useContext(AuthContext);

  useEffect(() => {
    if (isAuthenticated) {
      let service = () => {};
      switch (method) {
        case METHODS.GET:
          service = axios.get;
          break;
        case METHODS.POST:
          service = axios.post;
          break;
        default:
          break;
      }
      service(url, {
        headers: {
            'Authorization': `Bearer ${token}`
        },
      })
        .then((res) => {
          setData(res.data);
          setStatus(API_STATUS.success);
        })
        .catch((err) => setStatus(API_STATUS.errored));
    }
    // eslint-disable-next-line
  }, []);
  return [status === API_STATUS.loading, data, status === API_STATUS.errored];
}
export default useAPIHandler;

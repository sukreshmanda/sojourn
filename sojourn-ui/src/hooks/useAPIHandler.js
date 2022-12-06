import { useEffect, useState } from 'react'

const API_STATUS = {
    loading: "loading",
    success: "success",
    errored: "errored"
}
function useAPIHandler(service) {
    const [ data, setData ] = useState(null);
    const [ status, setStatus ] = useState(API_STATUS.loading)

    useEffect(() => {
        service().then(res => {
            setData(res.data)
            setStatus(API_STATUS.success)
        }).catch(err => setStatus(API_STATUS.errored))
        // eslint-disable-next-line
    }, [])
    return [status === API_STATUS.loading, data, status === API_STATUS.errored]
}
export default useAPIHandler;

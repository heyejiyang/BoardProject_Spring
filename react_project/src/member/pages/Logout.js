import React, { useContext, useEffect } from 'react';
import UserInfoContext from '../modules/UserInfoContext';
import { useNavigate } from 'react-router-dom';
import cookies from 'react-cookies';

const Logout = () => {
  const {
    states: { isLogin },
    actions: { setUserInfo, setIsLogin },
  } = useContext(UserInfoContext);
  cookies.remove('token', { path: '/' });
  setUserInfo(null);
  setIsLogin(false);

  const navigate = useNavigate();
  useEffect(() => {
    if (!isLogin) {
        //   navigate('/member/login', { replace: true });
        window.location.href = '/member/login';
    }
  }, [isLogin]);

  return <></>;
};

export default React.memo(Logout);

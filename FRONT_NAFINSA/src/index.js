import React from 'react';
import ReactDOM from 'react-dom';
import App from './routes/App';
import 'antd/dist/antd.min.css';
import './index.css';
import './components/tabStyle.css';
import es_ES from 'antd/lib/locale/es_ES';
import { ConfigProvider } from 'antd';
ReactDOM.render(
  <ConfigProvider locale={es_ES}>
  <App />
  </ConfigProvider>,
  document.getElementById('root')
);
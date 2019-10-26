var Mock = require('mockjs')
Mock.mock('/api/geturl', 'get', {
  status: 200,
  'data|5': [{
    'name': '@cname',
    'age|1-50': 50
  }]
})

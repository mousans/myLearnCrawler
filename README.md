# myLearnCrawler

#一.主要爬取数据内容
    B站用户的粉丝
#二.爬取后主要实现的功能
    1.输入B站的用户名或者id得到该用户的所有粉丝的列表
    2.输入两个b站用户名的id得到这两个用户之间的关系(ps:关系指的是关注和被关注的关系，如果两个用户之间没有直接的关注，被关注关系，也很有可能存在被动的关注和被关注关系)

#三.目前项目的主要难点
    1.在爬取数据的过程中,由于数据量过大(b站有几亿用户，如果单机的爬虫进行爬取花费时间太多，所以需要对任务进行分解，将其转变为分布式类型的爬虫进行爬取)
    2.由于数据量很大，所以为了时下面两个功能的用户体验更好，花费时间更短，我们需要认真考虑数据如何进行存储，方便我们对用户粉丝和用户关系进行查询

#四.未来进一步工作
    在完成上面的工作后，可以考虑继续实现对用户粉丝质量的分析(哪些是买的死粉丝，哪些是真正的粉丝)，用户质量(用户是否活跃)等功能。
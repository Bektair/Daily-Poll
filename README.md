# Daily-Poll

# NameSpace:
I don't own a domain, so reversed the local domain for now.
I have main config in root, seperated configuration files to allow the web layer to be tested in isolation.
I attempt to hold to the clean architecture, so I seperate entites, use-cases, presentation and data to make any dependencies that cross package boundaries visible. 
- arpa
   - home
     - springpoll
       - WebConfig
       - InitConfig
       - SpringpollApplication
       - presentation
         * PollController
         * QuestController
       - use-cases
         - AltSrvice
         - PollService
         - QuestService
       - entities
         - Poll
         - Question
         - Alternative
         - User
         - Vote
       - data
         - PollRepository
         - QuestionRepository
         - datasource
           - PgdatasourceconfigProd






         
         

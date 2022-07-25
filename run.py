  
import sys
import time
import subprocess


if __name__ == "__main__":
    tool = sys.argv[1]
    time_limit = "0.01"

    base_cov_port = 12000
    services = [
        "features-service",        # python3 report.py 12000 features-service
        "languagetool",            # python3 report.py 12010 languagetool
        "ncs",                     # python3 report.py 12020 ncs
        "news",                    # python3 report.py 12030 news
        "ocvn",                    # python3 report.py 12040 ocvn
        "proxyprint",              # python3 report.py 12050 proxyprint
        "restcountries",           # python3 report.py 12060 restcountries
        "scout-api",               # python3 report.py 12070 scout-api
        "scs",                     # python3 report.py 12080 scs
        "erc20-rest-service",      # python3 report.py 12090 erc20-rest-service
        "genome-nexus",            # python3 report.py 12100 genome-nexus
        "person-controller",       # python3 report.py 12110 person-controller
        "problem-controller",      # python3 report.py 12120 problem-controller
        "rest-study",              # python3 report.py 12130 rest-study
        "spring-batch-rest",       # python3 report.py 12140 spring-batch-rest
        "spring-boot-sample-app",  # python3 report.py 12150 spring-boot-sample-app
        "user-management",         # python3 report.py 12160 user-management
        "cwa-verification",        # python3 report.py 12170 cwa-verification
        "market",                  # python3 report.py 12180 market
        "project-tracking-system", # python3 report.py 12190 project-tracking-system
    ]
    for i in range(len(services)):
        cov_port = base_cov_port + i * 10
        print("Running " + tool + " for " + services[i] + ": " + str(cov_port))
        session = tool + '_' + services[i]
        cov_session = services[i] + "_cov"
        subprocess.run("tmux new -d -s " + cov_session + " sh get_cov.sh " + str(cov_port), shell=True)
        subprocess.run("tmux new -d -s " + session + " 'timeout " + time_limit + "h python3 run_tool.py " + tool + ' ' + services[i] + ' ' + str(cov_port) + "'", shell=True)

    # time.sleep(300)
    time.sleep(float(time_limit) * 60 * 60)

    subprocess.run("sh stop_all.sh", shell=True)
    for i in range(len(services)):
        subprocess.run("tmux kill-sess -t " + services[i], shell=True)
        subprocess.run("tmux kill-sess -t " + services[i] + "_cov", shell=True)
        subprocess.run("tmux kill-sess -t " + tool + '_' + services[i], shell=True)


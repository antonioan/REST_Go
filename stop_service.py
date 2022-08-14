import subprocess
import sys


if __name__ == "__main__":
    service = sys.argv[1]
    cov_port = sys.argv[2]
    subprocess.run(f"tmux kill-sess -t {service}_{cov_port}", shell=True)

    if service == "genome-nexus":
        subprocess.run(f"docker stop gn-mongo_{cov_port}", shell=True)
        subprocess.run(f"docker rm gn-mongo_{cov_port}", shell=True)
    elif service == "person-controller":
        subprocess.run(f"docker stop mongodb_{cov_port}", shell=True)
        subprocess.run(f"docker rm mongodb_{cov_port}", shell=True)
    elif service == "problem-controller":
        subprocess.run(f"docker stop mysql_{cov_port}", shell=True)
        subprocess.run(f"docker rm mysql_{cov_port}", shell=True)
    elif service == "user-management":
        subprocess.run(f"docker stop mysqldb_{cov_port}", shell=True)
        subprocess.run(f"docker rm mysqldb_{cov_port}", shell=True)

#!/bin/bash
# System monitoring report - Kozhanov Abdualim

STUDENT_NAME="Kozhanov Abdualim"
LOG_FILE="$HOME/devops/midterm/kozhanov-abdualim-student-api/logs/Kozhanov_Abdualim_system_report.log"

{
  echo "========== SYSTEM REPORT =========="
  echo "Student: $STUDENT_NAME"
  echo "Date and time: $(date '+%Y-%m-%d %H:%M:%S')"
  echo "Username: $(whoami)"
  echo

  echo "--- Operating system ---"
  grep PRETTY_NAME /etc/os-release | cut -d '"' -f 2
  echo "Kernel: $(uname -r)"
  echo

  echo "--- CPU ---"
  lscpu | grep -E "Model name|^CPU\(s\)"
  echo

  echo "--- Memory ---"
  free -h
  echo

  echo "--- Disk usage ---"
  df -h /
  echo

  echo "--- Available disk space ---"
  df -h / | awk 'NR==2 {print $4 " available on /"}'
  echo
} | tee -a "$LOG_FILE"

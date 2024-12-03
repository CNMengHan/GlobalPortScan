# GlobalPortScan

This program generates random IPv4 addresses, checks their accessibility on port 80, and attempts to open accessible IPs in the default system browser. It is implemented in Java and uses multithreading to process multiple IPs simultaneously.

---

## Features

- **Random IP Generation**: The program generates random IPv4 addresses excluding private ranges and reserved addresses like `127.x.x.x`.
- **Port 80 Accessibility Check**: It checks if the generated IPs are accessible on port 80 (HTTP).
- **Browser Integration**: If an IP is accessible, the program attempts to open it in the system's default web browser.
- **Thread Management**: The program uses multithreading to handle up to 20,000 threads for IP checking.

---

## Requirements

- Java 8 or higher
- A system supporting `java.awt.Desktop` for browser integration (e.g., most modern desktop OSes).

---

## How It Works

1. **Generate Random IPs**:
   - The `createRandomIp` method generates a random IPv4 address.
   - Private IP ranges (`10.x.x.x`, `172.16.x.x` to `172.31.x.x`, `192.168.x.x`) and loopback addresses (`127.x.x.x`) are excluded.

2. **Multithreaded IP Checking**:
   - Each IP is checked in a separate thread using the `check` method.
   - If port 80 is open on an IP, it logs the address and attempts to open it in the default browser.

3. **Thread Pool Management**:
   - The program limits the number of active threads to 20,000.
   - Completed threads are removed from the thread pool to free up resources.

---

## Running the Program

1. Compile the code using the Java compiler:
   ```bash
   javac main.java
   ```
2. Run the program:
   ```bash
   java main
   ```

---

## Notes

- **High Resource Usage**: The program uses up to 20,000 threads, which can be resource-intensive. Ensure your system has sufficient memory and CPU capacity.
- **Potential Risks**: Continuously probing random IPs may violate network policies or trigger security alerts. Use responsibly and with permission.
- **Browser Support**: The `Desktop` API may not be supported on all platforms. In such cases, the program logs the accessible IPs without attempting to open them.

---

## Disclaimer

This program is for educational purposes only. The authors are not responsible for any misuse or unintended consequences. Always ensure compliance with local laws and regulations.

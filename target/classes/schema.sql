CREATE TABLE IF NOT EXISTS ip_geolocation (
    ip_address TEXT PRIMARY KEY,
    country TEXT,
    countryCode TEXT,
    region TEXT,
    regionName TEXT,
    city TEXT,
    zip TEXT,
    lat REAL,
    lon REAL,
    timezone TEXT,
    isp TEXT,
    org TEXT,
    as_info TEXT,
    query TEXT,
    api_status TEXT,
    api_timestamp DATETIME DEFAULT CURRENT_TIMESTAMP
);
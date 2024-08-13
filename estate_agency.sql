-- -- Tạo database với mã hóa UTF-8
-- CREATE DATABASE estate_agency
--     WITH ENCODING 'UTF8'
--     LC_COLLATE='en_US.UTF-8'
--     LC_CTYPE='en_US.UTF-8'
--     TEMPLATE=template0;


CREATE TABLE account (
    id BIGSERIAL PRIMARY KEY, -- Mã tài khoản (1, 2, 3, ...)
    username VARCHAR(50) UNIQUE NOT NULL, -- Tên đăng nhập (VD: "user1")
    password_hash VARCHAR(255) NOT NULL, -- Mật khẩu đã mã hóa
    email VARCHAR(255) UNIQUE, -- Email (VD: "user1@example.com")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);

CREATE TABLE role (
    id SERIAL PRIMARY KEY, -- Mã vai trò (1, 2, 3, ...)
    name VARCHAR(50) UNIQUE NOT NULL, -- Tên vai trò (VD: "Admin", "User")
 	created_by INT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by INT, -- Người sửa (VD: 1, 2, ...)
    updated_by VARCHAR(100), -- Người sửa (VD: Hoang Van A)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);


CREATE TABLE permission (
    id SERIAL PRIMARY KEY, -- Mã quyền (1, 2, 3, ...)
    name VARCHAR(50) UNIQUE NOT NULL, -- Tên quyền (VD: "READ", "WRITE")
    description TEXT, -- Mô tả quyền (VD: "Quyền đọc dữ liệu")
	created_by INT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by INT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);

CREATE TABLE user_role (
    user_id BIGINT REFERENCES account(id), -- Mã tài khoản (VD: 1)
    role_id INT REFERENCES role(id), -- Mã vai trò (VD: 1)
    PRIMARY KEY (user_id, role_id),
	created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);


CREATE TABLE role_permission (
    role_id INT REFERENCES role(id), -- Mã vai trò (VD: 1)
    permission_id INT REFERENCES permission(id), -- Mã quyền (VD: 1)
    PRIMARY KEY (role_id, permission_id),
	created_by INT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by INT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);

CREATE TYPE property_type AS ENUM (
	'APARTMENT',
	'HOUSE',
	'VILLA'
);
CREATE TYPE property_status AS ENUM (
	'AVAILABLE',
	'RENTED',
	'SOLD',
	'UNDER_REPAIR'
);
CREATE TYPE property_direction AS ENUM(
	'EAST',
	'NORTH',
	'SOUTH',
	'WEST'
);
CREATE TABLE property (
    id BIGSERIAL PRIMARY KEY, -- Mã bất động sản (1, 2, 3, ...)
    type property_type, -- Loại bất động sản (Căn hộ, chung cư,...)
    code VARCHAR(50) UNIQUE NOT NULL, -- Mã bất động sản (VD: "PROP001")
    name VARCHAR(255) NOT NULL, -- Tên bất động sản (VD: "Căn hộ Vinhomes")
    address TEXT, -- Địa chỉ (VD: "72 Lê Thánh Tôn, Quận 1, TP.HCM")
    city VARCHAR(100), -- Tỉnh/Thành phố (VD: "TP.HCM")
    district VARCHAR(100), -- Quận/Huyện (VD: "Quận 1")
    ward VARCHAR(100), -- Phường/Xã (VD: "Phường Bến Nghé")
    area DECIMAL(10, 2), -- Diện tích (VD: 100.50)
    bedrooms INT, -- Số phòng ngủ (VD: 2)
    bathrooms INT, -- Số phòng tắm (VD: 2)
    built_year INT, -- Năm xây dựng (VD: 2019)
    direction property_direction, -- Hướng nhà (VD: "Đông")
    description TEXT, -- Mô tả (VD: "Căn hộ cao cấp")
    status property_status, -- Trạng thái (VD: "Trống", "Đã cho thuê", "Đã bán", "Đang sửa chữa")
    sale_price DECIMAL(18, 0), -- Giá bán (VD: 3000000000.00)
    rent_price DECIMAL(18, 0), -- Giá thuê (VD: 15000000.00)
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);


CREATE TYPE amenity_type AS ENUM(
	'INTERIOR',
	'EXTERIOR',
	'COMMON',
	'OTHER'
);
CREATE TABLE amenity(
	id BIGSERIAL PRIMARY KEY, -- Mã tiện ích (1, 2, 3, ...)
    name VARCHAR(100), -- Tên tiện ích (VD: "Hồ bơi")
    type amenity_type, -- Loại tiện ích (VD: "Nội thất", "Ngoại thất", "Tiện ích chung")
    description VARCHAR(255), -- Mô tả (VD: "Hồ bơi rộng 25m")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);
CREATE TABLE property_amenity (
    id BIGSERIAL PRIMARY KEY, -- Mã tiện ích (1, 2, 3, ...)
    property_id BIGINT REFERENCES property(id), -- Mã bất động sản (VD: 1)
   	amenity_id BIGINT REFERENCES amenity(id), -- Mã tiện ích
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);


CREATE TABLE property_image (
    id BIGSERIAL PRIMARY KEY, -- Mã hình ảnh (1, 2, 3, ...)
    property_id BIGINT REFERENCES property(id), -- Mã bất động sản (VD: 1)
    image_url TEXT, -- Đường dẫn hình ảnh (VD: "https://example.com/image.jpg")
    is_main BOOLEAN DEFAULT false, -- Hình ảnh chính (VD: TRUE)
    description VARCHAR(255), -- Mô tả (VD: "Hình ảnh mặt tiền")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);

CREATE TYPE customer_type as ENUM (
	'INDIVIDUAL',
	'BUSINESS',
	'OTHER'
);
CREATE TABLE customer (
    id BIGSERIAL PRIMARY KEY, -- Mã khách hàng (1, 2, 3, ...)
	account_id BIGINT REFERENCES account(id),
    code VARCHAR(50) UNIQUE NOT NULL, -- Mã khách hàng (VD: "CUST001")
    full_name VARCHAR(100), -- Họ tên khách hàng (VD: "Nguyễn Văn A")
    email VARCHAR(255) UNIQUE, -- Email (VD: "nguyenvana@example.com")
    phone VARCHAR(20), -- Số điện thoại (VD: "0901234567")
    address TEXT, -- Địa chỉ (VD: "123 Đường ABC, Quận 1, TP.HCM")
    birth_date DATE, -- Ngày sinh (VD: "1990-01-01")
    id_number VARCHAR(20) UNIQUE, -- CMND/CCCD (VD: "123456789")
    type customer_type, -- Loại khách hàng (VD: "Cá nhân", "Doanh nghiệp")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);

CREATE TYPE contract_type AS ENUM (
	'SALE',
	'RENT'
);

CREATE TYPE contract_status AS ENUM (
	'IN_PROGRESS',
	'COMPLETED',
	'CANCELLED'
);
CREATE TABLE contract (
    id BIGSERIAL PRIMARY KEY, -- Mã hợp đồng (1, 2, 3, ...)
    code VARCHAR(50) UNIQUE NOT NULL, -- Mã hợp đồng (VD: "CONT001")
    property_id BIGINT REFERENCES property(id), -- Mã bất động sản (VD: 1)
    customer_id BIGINT REFERENCES customer(id), -- Mã khách hàng (VD: 1)
    type contract_type, -- Loại hợp đồng (VD: "Mua bán", "Cho thuê")
    start_date DATE, -- Ngày bắt đầu (VD: "2023-01-01")
    end_date DATE, -- Ngày kết thúc (VD: "2023-12-31")
    value DECIMAL(18, 0), -- Giá trị hợp đồng (VD: 5000000000.00)
    deposit DECIMAL(18, 0), -- Tiền cọc (VD: 500000000.00)
    service_fee DECIMAL(18, 0), -- Phí dịch vụ (VD: 10000000.00)
    payment_method VARCHAR(100), -- Hình thức thanh toán (VD: "Chuyển khoản")
    terms TEXT, -- Điều khoản (VD: "Điều khoản hợp đồng")
    status contract_status, -- Trạng thái (VD: "Đang thực hiện", "Đã kết thúc", "Đã hủy")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);


CREATE TYPE maintenance_level AS ENUM(
	'LOW',
	'MEDIUM',
	'HIGH'
);
CREATE TYPE maintenance_status AS ENUM(
	'PENDING',
    'IN_PROGRESS',
    'COMPLETED',
    'CANCELLED'
);
CREATE TABLE maintenance (
    id BIGSERIAL PRIMARY KEY, -- Mã yêu cầu bảo trì (1, 2, 3, ...)
    property_id BIGINT REFERENCES property(id), -- Mã bất động sản (VD: 1)
    reported_by_id BIGINT REFERENCES customer(id), -- Mã khách hàng báo cáo (VD: 1)
    description TEXT, -- Mô tả (VD: "Sửa chữa hệ thống điện")
    reported_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày báo cáo (VD: "2023-01-01 00:00:00")
    priority_level maintenance_level, -- Mức độ ưu tiên (VD: 1)
    status maintenance_status, -- Trạng thái (VD: "Đang xử lý", "Hoàn thành")
    estimated_cost DECIMAL(18, 0), -- Chi phí dự kiến (VD: 1000000.00)
    actual_cost DECIMAL(18, 0), -- Chi phí thực tế (VD: 900000.00)
    completed_at TIMESTAMP, -- Ngày hoàn thành (VD: "2023-01-10 00:00:00")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);


CREATE TABLE employee (
    id BIGSERIAL PRIMARY KEY, -- Mã nhân viên (1, 2, 3, ...)
	account_id BIGINT REFERENCES account(id),
    code VARCHAR(50) UNIQUE NOT NULL, -- Mã nhân viên (VD: "EMP001")
    full_name VARCHAR(100), -- Họ tên nhân viên (VD: "Trần Văn B")
    email VARCHAR(255) UNIQUE, -- Email (VD: "tranvanb@example.com")
    phone VARCHAR(20), -- Số điện thoại (VD: "0912345678")
    address TEXT, -- Địa chỉ (VD: "456 Đường XYZ, Quận 2, TP.HCM")
    birth_date DATE, -- Ngày sinh (VD: "1985-05-15")
    id_number VARCHAR(20) UNIQUE, -- CMND/CCCD (VD: "987654321")
    education VARCHAR(100), -- Trình độ học vấn (VD: "Đại học")
    hire_date DATE, -- Ngày vào làm (VD: "2020-01-01")
    is_active BOOLEAN DEFAULT true, -- Đang làm việc (VD: TRUE)
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);

CREATE TABLE management_assignment (
    id BIGSERIAL PRIMARY KEY, -- Mã phân công (1, 2, 3, ...)
    property_id BIGINT REFERENCES property(id), -- Mã bất động sản (VD: 1)
    employee_id BIGINT REFERENCES employee(id), -- Mã nhân viên (VD: 1)
    start_date DATE NOT NULL, -- Ngày bắt đầu (VD: "2023-01-01")
    end_date DATE, -- Ngày kết thúc (VD: "2023-12-31")
    status VARCHAR(50) NOT NULL, -- Trạng thái (VD: "Đang quản lý", "Đã kết thúc")
    job_description TEXT, -- Mô tả công việc (VD: "Quản lý bảo trì")
    assigned_by INT REFERENCES employee(id), -- Người phân công (VD: 1)
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);

CREATE TABLE property_inspection (
    id BIGSERIAL PRIMARY KEY, -- Mã kiểm tra (1, 2, 3, ...)
    property_id BIGINT REFERENCES property(id), -- Mã bất động sản (VD: 1)
    inspector_id BIGINT REFERENCES employee(id), -- Mã nhân viên kiểm tra (VD: 1)
    inspection_date DATE, -- Ngày kiểm tra (VD: "2023-01-15")
    report TEXT, -- Báo cáo (VD: "Kiểm tra hệ thống điện")
    status VARCHAR(50), -- Trạng thái (VD: "Đạt", "Không đạt")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);


CREATE TABLE employee_activity (
    id BIGSERIAL PRIMARY KEY, -- Mã hoạt động (1, 2, 3, ...)
    employee_id BIGINT REFERENCES employee(id), -- Mã nhân viên (VD: 1)
    customer_id BIGINT REFERENCES customer(id), -- Mã khách hàng (VD: 1)
    property_id BIGINT REFERENCES property(id), -- Mã bất động sản (VD: 1)
    activity_type VARCHAR(100), -- Loại hoạt động (VD: "Gặp gỡ khách hàng")
    activity_time TIMESTAMP, -- Thời gian hoạt động (VD: "2023-01-20 10:00:00")
    location VARCHAR(255), -- Địa điểm (VD: "Cà phê Trung Nguyên")
    description TEXT, -- Mô tả (VD: "Thảo luận về hợp đồng")
    result TEXT, -- Kết quả (VD: "Thống nhất ký hợp đồng")
    cost DECIMAL(10, 2), -- Chi phí (VD: 200000.00)
    note TEXT, -- Ghi chú (VD: "Mang theo tài liệu hợp đồng")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);

CREATE TABLE payment (
    id BIGSERIAL PRIMARY KEY, -- Mã thanh toán (1, 2, 3, ...)
    contract_id BIGINT REFERENCES contract(id), -- Mã hợp đồng (VD: 1)
    amount DECIMAL(18, 0), -- Số tiền (VD: 5000000.00)
    payment_date DATE, -- Ngày thanh toán (VD: "2023-01-25")
    payment_method VARCHAR(50), -- Phương thức thanh toán (VD: "Chuyển khoản")
    status VARCHAR(50), -- Trạng thái (VD: "Đã thanh toán", "Chờ xử lý")
    note TEXT, -- Ghi chú (VD: "Thanh toán tháng 1")
    created_by BIGINT, -- Người tạo (VD:\ 1, 2, ....)     
	updated_by BIGINT, -- Người sửa (VD: 1, 2, ...)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày tạo (VD: "2023-01-01 00:00:00")
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP, -- Ngày sửa (VD: "2023-01-01 00:00:00")
    is_deleted BOOLEAN DEFAULT FALSE -- Trạng thái xóa (VD: FALSE)
);
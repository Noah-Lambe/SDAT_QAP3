-- Insert sample members
INSERT INTO members (id, name, email, address, phone_number, membership_start_date, membership_duration_in_months)
VALUES
(1, 'Alice Johnson', 'alice.johnson@example.com', '123 Maple Street', '709-123-4567', '2024-01-15', 12),
(2, 'Ben Carter', 'ben.carter@example.com', '456 Oak Avenue', '709-234-5678', '2024-02-10', 6),
(3, 'Charlie Kim', 'charlie.kim@example.com', '789 Birch Lane', '709-345-6789', '2024-03-05', 3),
(4, 'Diana Lopez', 'diana.lopez@example.com', '321 Pine Road', '709-456-7890', '2024-04-20', 9),
(5, 'Ethan Singh', 'ethan.singh@example.com', '654 Cedar Blvd', '709-567-8901', '2024-05-12', 18);

-- Insert sample tournaments
INSERT INTO tournaments (id, start_date, end_date, location, entry_fee, cash_prize)
VALUES
(1, '2024-08-01', '2024-08-03', 'St. John\'s Arena', 25.00, 500.00),
(2, '2024-09-15', '2024-09-17', 'Corner Brook Club', 30.00, 750.00);

-- Link members and tournaments (join table)
INSERT INTO tournament_members (member_id, tournament_id)
VALUES
(1, 1),
(2, 2),
(3, 1),
(4, 2),
(5, 1);
import { AccountType } from './account-type.enum';

export interface Account {

    id?: number;

    code: string;

    name: string;

    type: AccountType;

    active?: boolean;

    createdAt?: string;

    createdBy?: string;

    updatedAt?: string;

    updatedBy?: string;
}